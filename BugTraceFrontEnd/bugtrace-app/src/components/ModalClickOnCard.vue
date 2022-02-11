<template>

  <div class="myBackground" @click.self="close">
    <CreateNewBugCard @added="closeEverything" :card="card" v-if="modify"></CreateNewBugCard>
    <div v-if="!modify">
        <div  class="myModal">
          <h5 class="card-title">{{ card.title }}</h5>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: {{ card.creator }}<br>
              Created at: {{ card.dateCreated }}
            </h6>
          </div>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Assigned to: {{ card.assignedTo }}<br>
              Date assigned: {{card.dateAssigned}}
            </h6>
          </div>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Completed by: {{ card.completedBy }}<br>
              Date completed: {{card.dateCompleted}}
            </h6>
          </div>
          <div class="myDescription">
            <p>{{card.description}}</p>
          </div>
          <div class="myPills">
            <span class="badge rounded-pill bg-danger myPill">{{ card.impact }}</span>
            <span v-for="keyword in card.keywords" class="badge rounded-pill bg-primary myPill">{{keyword}}</span>
          </div>
          <div class="btn-group float-sm-end">
            <button type="button" class="btn btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Action
            </button>
            <div class="dropdown-menu">
              <a class="dropdown-item" @click="completeCard()" >Complete</a>
              <a class="dropdown-item" @click="modifyCard()" >Edit</a>
              <a class="dropdown-item" @click="removeCard()">Remove</a>
            </div>
          </div>
        </div>
  </div>
  </div>


</template>

<script>
import axios from "axios";
import CreateNewBugCard from "@/components/CreateNewBugCard";

export default {
  name: "ModalClickOnCard",
  components: {CreateNewBugCard},
  props:["card"],
  data(){return{
   modify:false
  }},
  created() {

  },
  methods:{
    close()
    {
      this.$emit("close") //custom event to identify if user wants to close
    },
    completeCard()
    {
      //sending data
      let self = this;
      axios.post('http://192.168.0.16:8080/app/modify', {
        email: this.$route.params.email,
        password: this.$route.params.password,
        username: this.$route.params.username,
        teamId: this.$route.params.teamId,
        title: this.card.title,
        assignTo:this.card.assignedTo,
        priority:this.card.impact,
        keywords:this.card.keywords,
        description:this.card.description,
        cardId: this.card.cardId,
        completedBy:this.$route.params.username}).then(function (response)
          {
          }
      );
      this.closeEverything();
      },
    removeCard()
    {
      let self = this;
      axios.post('http://192.168.0.16:8080/app/remove', {
        email: this.$route.params.email,
        password: this.$route.params.password,
        teamId: this.$route.params.teamId,
        cardId: this.card.cardId}).then(function (response) {
      });
      this.closeEverything();
      },
    modifyCard()
    {
      this.modify=true;
    },
    closeEverything()
    {
      this.modify=false;
      this.close();
    }
  }
}
</script>

<style scoped>
.myBackground
{
  top: 0;
  position: fixed;
  background: rgba(0,0,0,0.5);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;

}
.myModal
{
  background: white;
  margin-top: 6em;
  padding: 16px 16px 50px 16px;
  border-radius: 20px;
  min-width: 600px;
  max-width: 70%;

}
.myAuthorAndData
{
  display: inline-block;
  margin-right: 16px;
}

@media only screen and (max-width: 600px)
{
  .myModal
  {
    background: white;
    margin-top: 0em;
    padding: 16px 16px 16px 16px;
    border-radius: 20px;
    min-width: 600px;
    max-width: 100%;
    min-height: 100%;
    text-align: center;

  }
  .myAuthorAndData
  {
    display: block;
    margin-right: 0 ;

  }
  .myPill
  {
    margin-top: 8px;
    margin-right: 4px;
    margin-bottom: 8px;
  }
}


.myDescription
{
  margin-top: 20px;
  border-style: solid;
  border-width: 1px;
  border-color: rgba(0,0,0,0.2);
  padding: 16px;
}
.myPill
{
  margin-top: 8px;
  margin-right: 4px;
}

.card-title
{
  text-align: center;
}

</style>
