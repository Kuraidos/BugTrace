<template>
  <div class="container registerContainer">
    <h5 class="registerHeader">Create your account! It's free and only takes a minute.</h5>
    <div class="row">
      <div class="col-sm"></div>
      <div class="col-sm">
        <div class="loginForm" @submit.prevent="register">
          <form>
            <div class="form-group myInputs">
              <label for="username">Username</label>
              <input v-model="username" type="text" class="form-control" id="username"  placeholder="Josh">
            </div>
            <div class="form-group myInputs">
              <label for="email">Email address</label>
              <input v-model="email" type="email" class="form-control" id="email" placeholder="Example@email.com">
            </div>
            <div class="form-group myInputs">
              <label for="password">Password</label>
              <input v-model="password" type="password" class="form-control" id="password" placeholder="Enter Your Password">
            </div>
            <div class="form-group myInputs">
              <label for="passwordConfirm">Confirm Password</label>
              <input v-model="passwordRe" type="password" class="form-control" id="passwordConfirm" placeholder="Confirm Your Password">
            </div>
            <h6 v-if="error" style="color:red">{{error}}</h6>
            <button class="btn btn-primary myBigButton">Register</button>
          </form>

        </div>
      </div>
      <div class="col-sm"></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Register",
  data()
  {
    return{
       username:"",email:"",password:"",passwordRe:"",error:""
    }
  },
  methods: {
        register()
        {
          if(this.ConfirmPassword())
          {
              axios.post('http://192.168.0.16:8080/register', {
                username: this.username,
                email: this.email,
                password: this.password
                }).then(function (response) {
                this.checkServerError(response.data)
              }.bind(this));
          }
        },
        ConfirmPassword()
        {
          if(this.password.length<8 )
          {
            this.error="Password is too short! Password needs to be at least 8 characters long!"
            return false;
          }
          else if(this.password!=this.passwordRe)
          {
            this.error="Passwords do not match!"
            return(false);
          }
          else if(this.password==this.passwordRe)
          {
            return(true);
          }
          return false;
        },
        checkServerError(number)
        {
          if(number==1)
          {
            this.$router.push({name:"Login"});
          }
          else if(number==0)
          {
            this.error="Email is already taken!"
          }
        }
      },
      updated()
      {
          this.checkServerError()
      }

}
</script>

<style scoped>
.registerHeader
{
  text-align: center;
  margin: 27px 27px 0 0;
  opacity: 70%;
}
</style>
