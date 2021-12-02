<template>
  <nav-bar></nav-bar>
  <create-new-bug-card v-if="showCreateModal" style="z-index: 2" @added="closeCreateModal()"></create-new-bug-card>
  <modal-click-on-card @close='tempCard=""' :card="tempCard" v-if="tempCard" style="z-index: 2"></modal-click-on-card>
  <div class="container">
  <div class="row" >
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">To do</h5></div>
      <div class="addBugCard" @click="this.showCreateModal=true"><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card  v-for="card in pageData.toDos" :key="card.cardId" :card="card" @click="openCard(card)"></bug-card>
    </div>
    <div class="col-sm" >
      <div class="myColDiv"><h5 class="myColName">In Progress</h5></div>
      <div class="addBugCard" @click="this.showCreateModal=true" ><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card v-for="card in pageData.inProgress" :key="card.cardId" :card="card" @click="openCard(card)"></bug-card>
    </div>
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">Completed</h5></div>
      <div class="addBugCard" @click="this.showCreateModal=true"><h1 class="display-5 addSymbol">+</h1></div>
      <bug-card v-for="card in pageData.completed" :key="card.cardId" :card="card" @click="openCard(card)"></bug-card>
    </div>
  </div>
  </div>
</template>

<script>
import navBar from "@/components/navBar";
import BugCard from "@/components/BugCard";
import ModalClickOnCard from "@/components/ModalClickOnCard";
import CreateNewBugCard from "@/components/CreateNewBugCard";
import axios from "axios";
export default {
  name: "MainPage",
  components:
      {
        navBar, BugCard, ModalClickOnCard, CreateNewBugCard
      },
  props:
     {

     },
  updated()
  {

  },
  data(){
    return{
      pageData:{},showCreateModal:false,tempCard:""
    }},
  created()
  {
    this.user=this.$route.params
    this.getData()
    this.interval = setInterval(() => this.getData(), 5000);
  },
  methods:
      {
        getData()
        {
          let self = this;
          axios.post("http://localhost:8080/app",{email:this.$route.params.email,password:this.$route.params.password,teamId:this.$route.params.teamId}).then(function (response){
            self.pageData=response.data
          })
        },
        closeCreateModal()
        {
          this.getData()
          this.showCreateModal=false;
        },
        openCard(card)
        {
          this.tempCard=card;
        }
      }


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
