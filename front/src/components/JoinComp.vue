<template>
   
    <v-dialog v-model="dialog" max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on"
          icon
          darks
        >
          <v-icon>mdi-account-plus</v-icon>
        </v-btn>
      </template>
      
      <v-stepper v-model="stage" vertical id="stepper">
        <div class="text-center"><img src="../assets/GaBom.svg" class="img-fluid pa-3" alt="Logo" width="200" height="50"/></div>
        <v-sheet class="headline mb-2 text-center"> 회원 가입 </v-sheet>

        <v-stepper-step step="1" :complete="stage > 1">
          Terms of Service
        </v-stepper-step>

        <v-stepper-content step="1">
          <v-card-text
            class="mx-auto mb-5"
            max-width="300"
            outlined
            height="200px"
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title class="headline text-left">약관 동의</v-list-item-title>
                <v-list-item-subtitle class="text-left">개인 정보 수집 및 이용 </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <div class="text--primary text-left px-3">
              수집 및 이용 정보 : 회원 아이디, 이메일 주소, 성명<br>
              이용 목적: 회원 관리<br>
              이용에 동의하시면 동의 버튼을 눌러주세요.
            </div>
          </v-card-text>

          <v-sheet class="text-center">
            <v-btn color="primary" @click="stage = 2" outlined class="mr-2">동의</v-btn>
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>비동의</v-btn>
          </v-sheet>
        </v-stepper-content>


      <v-stepper-step step="2" :complete="stage > 2">ID/Password</v-stepper-step>
        <v-stepper-content step="2">
          <v-card
            class="mx-auto px-5 mb-5"
            max-width="500"
            outlined
          >

          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="headline text-left">로그인 정보 입력</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-form
            ref="form"
            lazy-validation
          >
          <v-row>
            <v-text-field
              v-model="form.userId"
              ref="userId"
              :counter="20"
              :rules="valid.userId"
              label="아이디"
              placeholder="6글자 이상, 영문 대/소문자 및 숫자"
              required
              maxlength="20"
            ></v-text-field>

            <v-btn width="8" height="40" @click="validateLoginId()">
              중복
            </v-btn>
            

          </v-row>
         
          <v-text-field
            v-model="form.email"
            ref="email"
            :rules="valid.email"
            label="E-mail"
            placeholder=" "
            required
          ></v-text-field>

          <v-text-field
            v-model="form.userPw"
            ref="userPw"
            :append-icon="options.userPwShow ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="valid.userPw"
            :type="options.userPwShow ? 'text' : 'userPw'"
            name="input-10-1"
            label="패스워드"
            placeholder="최소 10글자, 대/소문자 구분"
            counter
            required
            @click:append="options.userPwShow = !options.userPwShow"
          />

          <v-text-field
            ref="userPw2"
            :append-icon="options.userPwShow ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="valid.userPw2"
            :type="options.userPwShow ? 'text' : 'userPw'"
            name="input-10-2"
            label="패스워드 확인"
            placeholder=" "
            counter
            required
            @click:append="options.userPwShow = !options.userPwShow"
          />
          </v-form>

          </v-card>

          <v-sheet class="text-center">
            <v-btn color="primary" @click="submit" outlined :disabled="!form1OK" class="mr-2">확인</v-btn>
            <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear()" outlined>취소</v-btn>
            <v-alert
              v-show="errorMessage"
              type="error"
              dense
              outlined
            >
            {{ errorMessage }}
            </v-alert>
          </v-sheet>

          </v-stepper-content>

      <v-stepper-step step="3" :complete="stage > 3">
        Setup Names
      </v-stepper-step>

      <v-stepper-content step="3">
        <v-card
          class="mx-auto px-6 mb-5"
          max-width="500"
          outlined>

        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title class="headline text-left">회원 정보 입력</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-form
          ref="form2"      
          lazy-validation
        >

          <v-text-field
            v-model="form.userName"
            ref="userName"
            :counter="10"
            :rules="valid.userName"
            label="이름"
            placeholder="이름을 입력하세요 (최대 10자)"
            required
            maxlength="10"
          ></v-text-field>

          <v-row>
            <v-text-field
              v-model="form.nickName"
              ref="nickName"
              :rules="valid.nickName"
              label="닉네임"
              :counter="10"
              placeholder="닉네임을 입력하세요 (최대 10자)"
              required
              maxlength="10"
           ></v-text-field>  

            <v-btn width="8" height="40" @click="validateNickName()">
                중복
            </v-btn>
          </v-row>
          

        </v-form>
        </v-card>
        <v-sheet class="text-center">
          <loading-spinner v-if="isLoading"></loading-spinner>
          <v-btn color="primary" @click="stage = 2" outlined class="mr-2">이전</v-btn>
          <v-btn color="primary" @click="submit" outlined :disabled="!form2OK" class="mr-2">다음</v-btn>
          <v-btn color="grey darken-1" @click="dialog=false, stage = 1" outlined>취소</v-btn>
        </v-sheet>

        </v-stepper-content>

      

      <v-stepper-step step="4">
        View setup instructions
      </v-stepper-step>

      <v-stepper-content step="4">
        <v-card-text
          class="mx-auto mb-5"
          max-width="300"
          outlined
          height="200px"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="display-2">축하합니다!</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

            <div class="text--primary px-3">
              <img src="../assets/svcLogoWithoutFrames.svg" class="img-fluid mx-2" alt="Logo" width="100" height="50"/><br>
              가입하신 것을 진심으로 환영합니다. <!--이메일 인증을 진행하신후 로그인 서비스를 하실 수 있습니다.--><br>
              ({{ counter }}초후 자동으로 닫힙니다.)
            </div>
         </v-card-text>
         <v-btn color="grey darken-1" @click="dialog=false, stage = 1, clear" outlined>창 닫기</v-btn>
      </v-stepper-content>
    </v-stepper>   
    </v-dialog>
  
</template>

<script>
import {joinUser , validateLoginId , validateNickName} from '../api/auth'
import LoadingSpinner from './LoadingSpinner.vue'

export default {
    name : 'JoinComp',
    components : {
      LoadingSpinner
    },
    data () {
      return {
        isLoading : false,
        dialog: false,
        stage: 1,
        counter: 7,
        errorMessage: '',
        form: {
          userId: null,
          userPw: null,
          email: null,
          userName: null,
          nickName: null
        },
        options: {
          userPwShow: false
        },
        valid: {
          userId: [
            v => !!v || '아이디를 입력하세요.',
            v => (v && v.length <= 20 && v.length >= 6) || '아이디는 6글자 이상, 20글자 이하입니다.'
          ],
          userPw: [
            v => !!v || '패스워드를 입력하세요.',
            v => (v && v.length >= 10) || '패스워드는 10글자 이상입니다.'
          ],
          userPw2: [
            v => (v === this.form.userPw) || '패스워드가 일치하지 않습니다.'
          ],
          email: [
            v => !!v || '이메일 주소를 입력하세요.',
            v => /.+@.+\..+/.test(v) || '이메일 주소 형식이 맞지 않습니다.'
          ],
          userName: [
            v => !!v || '이름을 입력하세요.'
          ],
          nickName: [
            v => !!v || '닉네임을 입력하세요.'
          ]
        }
      }
    },
    computed: {
      form1OK () {
        let ok = false
        if (this.form.userId && this.form.userPw && this.form.email) {
          ok = true
        }
        return ok
      },
      form2OK () {
        let ok = false
        if (this.form.userName && this.form.nickName) {
          ok = true
        }
        return ok
      }
  },
  methods:{
    async submit(){
      
      if(this.stage === 2 &&  this.$refs.userId.validate() &&
        this.$refs.userPw.validate() &&
        this.$refs.userPw2.validate() &&
        this.$refs.email.validate()){
        console.log("stage2 -> stage3")
        this.stage = 3
      }else if(this.stage === 3 &&  this.$refs.userName.validate() && this.$refs.nickName.validate()){
        console.log(this.form)

        try{
          this.isLoading = true
          const response = await joinUser(this.form);
          this.isLoading = false

          console.log(response.data.code)
          //아이디가 중복 , 1005
          //회원중복 , 1004
          //닉네임 중복 , 1006
          if(response.data.code !== 0 ){
            this.errorMessage = '유저 등록에 실패했습니다. '+ response.data.msg
            this.stage = 2
          }else{
            console.log(response.data)
            this.stage = 4
            this.countDown()
            this.clear()
          }
          
        }catch(error){
          console.log(error);
          this.errorMessage = '유저 등록에 실패했습니다. ' + error.message
          this.stage = 2
        }

      }
    },
    async validateLoginId(){
      try{
        const response = await validateLoginId(this.form.userId);
        if(response.data.code !== 0){
          alert("이미 존재하는 아이디 입니다.")

        }else{
          alert("사용가능한 아이디 입니다.")
        }
        
        
      }catch(error){
        console.log(error);
      }
    },
    async validateNickName(){
      try {
        const response = await validateNickName(this.form.nickName);
        if(response.data.code !== 0 ){
          alert("이미 존재하는 닉네임 입니다.")

        }else{
          alert("사용가능한 닉네임 입니다.")
        }

      } catch (error) {
        console.log(error);
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