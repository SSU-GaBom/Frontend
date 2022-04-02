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
          아이디 찾기
        </v-btn>
      </template>

      <v-stepper v-model="stage" vertical id="stepper">
        <div class="text-center"><img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid pa-3" alt="Logo" width="200" height="50"/></div>
        <v-sheet class="headline mb-2 text-center"> 아이디 찾기 </v-sheet>

        <v-stepper-step step="1" :complete="stage > 1">
          본인 이메일을 통해 인증번호 받기
        </v-stepper-step>
        <v-stepper-content step="1">
          <v-card
            class="mx-auto px-5 mb-5"
            max-width="500"
            min-height="150"
            outlined
          >
            <v-form
              ref="form"
              lazy-validation
              v-model="hasError"
              class="pa-5 ma-5"
            >
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

        <v-stepper-step step="2" :complete="stage > 2">인증번호 입력하기</v-stepper-step>
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
          아이디 확인
        </v-stepper-step>
        <v-stepper-content step="3">
          <v-card-text
            class="mx-auto mb-5"
            max-width="300"
            outlined
            height="200px"
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title class="display-2">이곳에 아이디 출력</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-card-text>       
          <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear" outlined>창 닫기</v-btn>
        </v-stepper-content>

      </v-stepper>  
    </v-dialog>  
</template>

<script>
import {joinUser} from '../api/index'

export default {
    name : 'FindId',
    data () {
      return {
        dialog: false,
        stage: 1,
        counter: 5,
        form: {
          email: null,
          name: null
        },
        valid: {
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