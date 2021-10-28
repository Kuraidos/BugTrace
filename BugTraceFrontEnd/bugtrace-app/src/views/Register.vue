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
              <input type="text" class="form-control" id="username"  placeholder="Josh">
            </div>
            <div class="form-group myInputs">
              <label for="email">Email address</label>
              <input type="email" class="form-control" id="email" placeholder="Example@email.com">
            </div>
            <div class="form-group myInputs">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" placeholder="Enter Your Password">
            </div>
            <div class="form-group myInputs">
              <label for="passwordConfirm">Confirm Password</label>
              <input type="password" class="form-control" id="passwordConfirm" placeholder="Confirm Your Password">
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
      formData: { userId:"Hello"}
    }
  },
  methods: {
        register()
        {
          console.log("Hello")
          axios.post('http://localhost:8080/register',this.formData).then(response =>{console.log(response)})
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
