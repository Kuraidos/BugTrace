<template>
  <nav-bar></nav-bar>
  <div class="container">
  <div class="row">
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">To do</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card v-for="card in team.toDos" :key="card.cardId" :card="card"></bug-card>
    </div>
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">In Progress</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card v-for="card in team.inProgress" :key="card.cardId" :card="card"></bug-card>
    </div>
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">Completed</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card v-for="card in team.completed" :key="card.cardId" :card="card"></bug-card>
    </div>
  </div>
  </div>
</template>

<script>
import navBar from "@/components/navBar";
import BugCard from "@/components/BugCard";
import ModalClickOnCard from "@/components/ModalClickOnCard";
import CreateNewBugCard from "@/components/CreateNewBugCard";
export default {
  name: "MainPage",
  components:
      {
        navBar, BugCard, ModalClickOnCard, CreateNewBugCard
      },
  data(){
    return{
      user:{
        username:"",
        password:"",
        email:"",
        teamId:""},

      team:{
        name:"",
        teamId:"",
        teamMembers:
            [{user:
                  {
                    username:"",
                    password:"",
                    email:"",
                    teamId:""
                  },

              level:""
            }],

        toDos: [],
        inProgress:[{title:"Crashing Cars",cardId:"12",creator:"Klaidas Serelis",dateCreated:"11/3/2021",dateCompleted:"",dateAssigned:"", assignedTo:"", completedBy:"", description:"", impact:"High", keywords:[{keyword:"Zoom"},{keyword:"WromWrom"}]},],
        completed:[]}
    }}
}
</script>

<style scoped>
.myColName
{
  opacity: 50%;
}
.myColDiv
{
  text-align: center;
}
.addBugCard
{
  width: 100%;
  text-align: center;
}
.addSymbol
{
  position: relative;
  top: -15px;
  opacity: 50%;
}
</style>
