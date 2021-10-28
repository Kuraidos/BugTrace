<template>
  <div class="container">
    <h1  class="myHeader">Best BugTracking software for big and small!</h1>
    <div class="row">
      <div class="col-sm"></div>
      <div class="col-sm">
        <div class="loginForm">
          <form>
            <div class="form-group myInputs">
              <label for="email">Email address</label>
              <input type="email" class="form-control" id="email"  placeholder="Example@email.com">
            </div>
            <div class="form-group myInputs">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" placeholder="Enter Your Password">
            </div>
            <div class="form-check">
              <label for="exampleCheck1">Remember Me</label>
              <input type="checkbox" class="form-check-input" id="exampleCheck1">
              <a href="#" class="forgotPassword">Forgot password?</a>
            </div>
            <button class="btn btn-primary myBigButton">Login</button>
          </form>
        </div>
        <div class="sighUp">
          <p class="p-0">Don't have an Account? <a href="/register">Sign up now!</a>
            <br><a href="#">Demo</a>
          </p>
        </div>
      </div>
      <div class="col-sm"></div>
    </div>
  </div>
</template>

<script>


export default {
  name: 'Login',
  components: {

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
