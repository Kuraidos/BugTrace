<template>
  <div class="container">
    <h1  class="myHeader">Best BugTracking software for big and small!</h1>
    <div class="row">
      <div class="col-sm"></div>
      <div class="col-sm">
        <div class="loginForm" @submit.prevent="login">
          <form>
            {{test}}
            <div class="form-group myInputs">
              <label for="email">Email address</label>
              <input v-model="email" type="email" class="form-control" id="email"  placeholder="Example@email.com">
            </div>
            <div class="form-group myInputs">
              <label for="password">Password</label>
              <input v-model="password" type="password" class="form-control" id="password" placeholder="Enter Your Password">
            </div>
            <div class="form-check">
              <label for="exampleCheck1">Remember Me</label>
              <input type="checkbox" class="form-check-input" id="exampleCheck1">
              <router-link class="forgotPassword" to="/">Forgot password?</router-link>
            </div>
            <button class="btn btn-primary myBigButton">Login</button>
          </form>
        </div>
        <div class="sighUp">
          <p class="p-0">Don't have an Account? <router-link :to="{ name: 'Register'}">Sign up now!</router-link>
            <br><router-link to="/">Demo</router-link>
          </p>
        </div>
      </div>
      <div class="col-sm"></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data()
  {
    return{
      email:"",password:"",test:"No CLick"
    }
  },
  methods: {
    login: function ()
    {
      let self = this;
      let password = this.password;
      let email = this.email;

      axios.post(process.env.VUE_APP_ROOT_URL+'app', {email: email, password: password}).then(function (response) {
        if(response.data!="")
        {
          self.$router.push({name:"MainPage",params:{username: (response,email),password: password, email:email, teamId: response.data.teamId}});
        }

      });
    }
    ,getUsername(response,email)
    {
      let username="";
      response.teamMembers.forEach(function(member) {if(member.email==email){username=member.username}});
    }
  }
}
</script>

<style scoped>
.myHeader
{
  margin: 47px auto;
  text-align: center;
  opacity: 70%;
}
.loginForm
{
  display: flex;
  justify-content: center;
}
.forgotPassword
{
  margin: 0 0 0 110px;
}
.sighUp
{
  text-align: center;
  margin: 47px auto;
}
</style>
