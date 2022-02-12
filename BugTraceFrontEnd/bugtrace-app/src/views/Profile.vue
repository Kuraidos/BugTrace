<template>
  <nav-bar></nav-bar>
  <div class="container">
    <div class="row">
      <div class="col-4">
        <h5 style="text-align: center">Profile</h5>
        <ul class="list-group">
          <li class="list-group-item active">Details</li>
          <li class="list-group-item">Teams</li>
          <li class="list-group-item">Logout</li>
        </ul>
      </div>
      <div class="col-8">
        <h5 style="text-align: center">Teams</h5>
        <ul class="list-group">
          <li class="list-group-item">
            <div class="myTeam">
              <p class="teamValues action">Invitation</p>
              <p class="teamValues">serelisltu@gmail.com</p>
              <div class="myButtons teamValues">
                <button type="button" class="btn btn-success">Accept</button>
                <button type="button" class="btn btn-danger">Ignore</button>
              </div>
            </div>
          </li>
          <li class="list-group-item">
            <div class="myTeam">
              <p class="teamValues action">Active</p>
              <p class="teamValues">serelisltu@gmail.com</p>
              <div class="myButtons teamValues">
                <button type="button" class="btn btn-success">Accept</button>
                <button type="button" class="btn btn-danger">Ignore</button>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import navBar from "@/components/navBar";
export default {
  name: "Profile",
  components:
{
  navBar
}
}
</script>

<style scoped>
.container
{
  padding-top: 2em;
}
.teamValues
{
  display:inline;
  margin-right: 2em;
}
.myButtons
{
  margin-right: 0;
  float: right;
}

.action
{
  display: inline-block;
  min-width: 65px;
}

</style>