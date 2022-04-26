<template>
  <nav-bar></nav-bar>
  <div class="container">
    <div class="row">
      <div class="col-4">
        <h5 style="text-align: center">Profile</h5>
        <ul class="list-group">
          <li class="list-group-item">Details</li>
          <li class="list-group-item active">Teams</li>
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
          <li class="list-group-item" v-for="team in teams">
            <div class="myTeam">
              <p class="teamValues action">{{team.level}}</p>
              <p class="teamValues">{{team.nameOfTeam}}</p>
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
import axios from "axios";
export default {
  name: "Profile",
  components:
{
  navBar
},
  data()
  {
    return{
      teams:{}

    }
  },
  created()
  {
    this.getTeams();
  },
  methods:
  {
    getTeams()
    {
      let self = this;
      axios.post(process.env.VUE_APP_ROOT_URL+"teamDetailsProfile",{email:this.$route.params.email,password:this.$route.params.password}).then(function (response){
        self.teams=response.data;
        console.log(response.data);

      })
    }
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