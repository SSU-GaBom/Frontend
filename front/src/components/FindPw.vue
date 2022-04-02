<template>
    <v-dialog v-model="dialog" max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          class="ma-2"
          text
          small
          color="#9E9E9E"
        >
          비밀번호 찾기
        </v-btn>
      </template>

      <v-stepper v-model="stage" vertical id="stepper">
        <div class="text-center"><img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid pa-3" alt="Logo" width="200" height="50"/></div>
        <v-sheet class="headline mb-2 text-center"> 비밀번호 찾기 </v-sheet>

        <v-stepper-step step="1" :complete="stage > 1">
          본인 이메일을 통해 인증번호 받기
        </v-stepper-step>

        <v-stepper-content step="1">
          <v-card
            class="mx-auto px-5 mb-5"
            max-width="500"
            height="300"
            outlined
          >
            <v-form
              ref="form"
              lazy-validation
              v-model="hasError"
              class="pa-5 ma-5"
            >

            <v-text-field
              v-model="form.loginId"
              ref="loginId"
              :counter="20"
              :rules="valid.loginId"
              label="아이디"
              placeholder="6글자 이상, 영문 대/소문자 및 숫자"
              required
              maxlength="20"
            ></v-text-field>

            <v-text-field
              v-model="form.name"
              ref="name"
              :counter="10"
              :rules="valid.name"
              label="이름"
              placeholder="회원가입 시 등록한 본인 이름을 입력하세요"
              required
              maxlength="10"
            ></v-text-field>

            <v-text-field
              v-model="form.email"
              ref="email"
              :rules="valid.email"
              label="E-mail"
              placeholder=" "
              required
            ></v-text-field>

            </v-form>
          </v-card>
          <v-sheet class="text-center">
            <v-btn color="primary" @click="stage = 2" outlined class="mr-2">확인</v-btn>
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>취소</v-btn>
          </v-sheet>
        </v-stepper-content>


        <v-stepper-step step="2" :complete="stage > 2">
          인증번호 입력하기
        </v-stepper-step>

        <v-stepper-content step="2">
          <v-card
            class="mx-auto px-6 mb-5"
            max-width="500"
            min-height="100"
            outlined
          >
            <v-form
              ref="form2"      
              lazy-validation
              v-model="hasError"
              class="pa-5 ma-5"
            >
              <v-text-field
                v-model="form.auth"
                ref="auth"
                :counter="10"
                :rules="valid.auth"
                label="인증번호"
                placeholder="인증번호를 입력하세요"
                required
                maxlength="10"
              ></v-text-field>
            </v-form>
          </v-card>
          <v-sheet class="text-center">
            <v-btn color="primary" @click="stage = 3" outlined class="mr-2">확인</v-btn>
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear()" outlined>취소</v-btn>
          </v-sheet>
        </v-stepper-content>


        <v-stepper-step step="3">
          비밀번호 재설정
        </v-stepper-step>

        <v-stepper-content step="3">
          <v-card
            class="mx-auto px-5 mb-5"
            max-width="500"
            outlined
          >            
            <v-form
              ref="form2"      
              lazy-validation
              v-model="hasError"
              class="pa-5 ma-5"
            >
              <v-text-field
                v-model="form.password"
                ref="password"
                :append-icon="options.passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="valid.password"
                :type="options.passwordShow ? 'text' : 'password'"
                name="input-10-1"
                label="패스워드"
                placeholder="최소 10글자, 대/소문자 구분"
                counter
                required
                @click:append="options.passwordShow = !options.passwordShow"
              ></v-text-field>

              <v-text-field
                ref="password2"
                :append-icon="options.passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="valid.password2"
                :type="options.passwordShow ? 'text' : 'password'"
                name="input-10-2"
                label="패스워드 확인"
                placeholder=" "
                counter
                required
                @click:append="options.passwordShow = !options.passwordShow"
              ></v-text-field>

            </v-form>
          </v-card>
          <v-sheet class="text-center">
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear()" outlined>확인</v-btn>
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear()" outlined>취소</v-btn>
          </v-sheet>
        </v-stepper-content>

      </v-stepper>  
    </v-dialog>  
</template>

<script>
import {joinUser} from '../api/index'

export default {
    name : 'FindPw',
    data () {
      return {
        dialog: false,
        stage: 1,
        counter: 5,
        form: {
          loginId: null,
          password: null,
          email: null,
          name: null
        },
        options: {
          passwordShow: false
        },
        valid: {
          loginId: [
            v => !!v || '아이디를 입력하세요.',
            v => (v && v.length <= 20 && v.length >= 6) || '아이디는 6글자 이상, 20글자 이하입니다.'
          ],
          password: [
            v => !!v || '패스워드를 입력하세요.',
            v => (v && v.length >= 10) || '패스워드는 10글자 이상입니다.'
          ],
          password2: [
            v => (v === this.form.password) || '패스워드가 일치하지 않습니다.'
          ],
          email: [
            v => !!v || '이메일 주소를 입력하세요.',
            v => /.+@.+\..+/.test(v) || '이메일 주소 형식이 맞지 않습니다.'
          ],
          name: [
            v => !!v || '이름을 입력하세요.'
          ]
        }
      }
    },
    computed: {
      form1OK () {
        let ok = false
        if (this.form.loginId && this.form.password && this.form.email) {
          ok = true
        }
        return ok
      },
      form2OK () {
        let ok = false
        if (this.form.name && this.form.nickName) {
          ok = true
        }
        return ok
      }
  },
  methods:{
    submit(){
      if(this.stage === 2 &&  this.$refs.loginId.validate() &&
        this.$refs.password.validate() &&
        this.$refs.password2.validate() &&
        this.$refs.email.validate()){
        console.log("stage2 -> stage3")
        this.stage = 3
      }else if(this.stage === 3 &&  this.$refs.name.validate() && this.$refs.nickName.validate()){
        console.log(this.form)
        joinUser(this.form).then( () => {
          this.stage = 4
          this.countDown()
          this.clear()
        }).catch( (error) => {
          this.errorMessage = '유저 등록에 실패했습니다. ' + error.message
          this.stage = 2
        })

      }
    },
    countDown () {
      if (this.counter > 0) {
        setTimeout(() => {
          this.counter--
          this.countDown()
        }, 1000)
      } if (this.counter === 0) {
        this.dialog = false
        this.stage = 1
        this.counter = 5
        this.clear()
      }
    },
    clear () {
      this.$refs.form.reset()
      this.$refs.form2.reset()
    }
  }
}
</script>


<style scoped>
#stepper{
  font-family: 'Noto Sans KR', sans-serif;
}
</style>