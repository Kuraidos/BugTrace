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
            <button class="btn btn-primary myBigButton">Register</button>
          </form>
        </div>
      </div>
      <div class="col-sm"></div>
    </div>
  </div>
  <div></div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Register",
  data()
  {
    return{
       username:"",email:"",password:"",passwordRe:"",error: ""
    }
  },
  methods: {
        register()
        {
          this.error="";
          if(this.ConfirmPassword())
          {
              axios.post('http://localhost:8080/register', {username: this.username, email: this.email, password: this.password}).then(response =>{
              if(response.data!=1)
              {
                this.ServerError(response.data)
              }
              console.log(response.data)})
          }
          console.log(this.error)
        },
        ConfirmPassword()
        {
          if(this.password==this.passwordRe)
          {
            return(true);
          }
          else if(this.password.isEmpty() || this.password.length<8 )
          {
            this.error="Password is too short!"
          }
          return false;
        },
        ServerError(number)
        {
          this.error="Error"
        }
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
