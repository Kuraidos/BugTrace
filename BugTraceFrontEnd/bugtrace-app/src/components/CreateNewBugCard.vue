<template>
  <div class="myBackground" @click.self="addedEvent()">
        <div @click.self="addedEvent" class="centerCol">
          <div class="myModal">
            <div>
              <div class="formTitleDiv">
                <h5 class="formTitle">{{ formTitle }}</h5>
              </div>
              <form @submit.prevent>
                <div class="form-group myInputs">
                  <label for="title">Name of a Bug</label>
                  <input v-model="title" type="text" class="form-control" id="title"  placeholder="Crashing Zoom">
                </div>
                <div class="form-group myInputs">
                  <label for="assignTo">Assign to </label>
                  <input v-model="assignTo" type="text" class="form-control" id="assignTo" placeholder="None">
                </div>
                <div class="form-group myInputs">
                  <label>Priority</label>
                  <select @click="pillColor" v-model="priority" class="form-control" >>
                    <option>HIGH</option>
                    <option>MEDIUM</option>
                    <option>LOW</option>
                  </select>
                </div>
                <div class="form-group myInputs">
                  <label for="keywords" class="myLabel">Add Keywords</label>
                  <input v-model="tempKeyword" @keyup="addKeyword" type="text" class="form-control keywordInput" id="keywords" placeholder="Zoom">
                  <button type="button" class="btn btn-primary addButton" @click="addKeyword">Add</button>
                  <div class="myPills">
                    <span class="badge rounded-pill bg-success myPill" id="priority">{{ priority }}</span>
                    <span v-for="keyword in keywords " class="badge rounded-pill bg-primary myPill">{{ keyword }}</span>
                  </div>
                </div>
                <div class="form-group">
                  <label>Description</label>
                  <textarea v-model="description" class="form-control" id="descriptio" rows="8" style="resize: none"></textarea>
                </div>
                <h6 style="color:red" v-if="error">{{error}}</h6>
                <button id="myButton" @click="postWhere" class="btn btn-primary myBigButton">{{ buttonText }}</button>
              </form>
            </div>
          </div>
        </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateNewBugCard",
  data(){
    return{
        title:"",assignTo:"",priority:"LOW",keywords:[],description:"",tempKeyword:"",type:"",buttonText:"Create",formTitle:"Bug Card Creation",error:""
    }},
  props:["card"],
  created()
  {
    if(this.card)
    {
      this.modifyData();
    }
  },
  methods: {
    create()
    {if(this.checkCorrectness())
    {
      let self = this;
      axios.post(process.env.VUE_APP_ROOT_URL+'app/create', {email: this.$route.params.email, password: this.$route.params.password,username: this.$route.params.username,teamId: this.$route.params.teamId,
        title: this.title,assignTo:this.assignTo,priority:this.priority,keywords:this.keywords,description:this.description}).then(function (response) {
      });
      this.addedEvent()
    }
    },
    addKeyword(e)
    {
      console.log(e)
      if((e.key==='Enter' || e.key===',' || e.type==='click') && this.tempKeyword!="")
      {
          this.keywords.push(this.tempKeyword.replaceAll(",",""))
          this.tempKeyword=""
      }

    },checkCorrectness()
    {
      this.error="";
      let correct=true;
      if(this.title=="")
      {
        this.error+="• Tittle cannot be empty!\n"
        correct=false;
      }
      return(correct);
    },
    modifyData()
    {

      this.title=this.card.title;
      this.assignTo=this.card.assignedTo;
      this.priority=this.card.impact;
      this.keywords=this.card.keywords;
      this.description=this.card.description;
      this.buttonText="Modify";
      this.formTitle="Bug Card Modification";

    },
    modify()
    {
      //Need to find if is possible to group the data for easier data transmit
      let self = this;
      axios.post(process.env.VUE_APP_ROOT_URL+'app/modify', {
        email: this.$route.params.email,
        password: this.$route.params.password,
        username: this.$route.params.username,
        teamId: this.$route.params.teamId,
        title: this.title,
        assignTo:this.assignTo,
        priority:this.priority,
        keywords:this.keywords,
        description:this.description,
        cardId: this.card.cardId,
        completedBy:this.card.completedBy}).then(function (response) {
      });
      this.addedEvent()
    },
    postWhere()
    {
      if(this.card)
      {
        this.modify();
      }
      else
      {
        this.create();
      }
    }
    ,
    addedEvent()
    {
      this.$emit("added") //custom event, to help close
    },
    whatType()
    {
      if(this.assignTo)
      {
        this.type="InProgress"
      }
      else
      {
        this.type="ToDo"
      }
    },
    pillColor()
    {
      let pill = document.getElementById("priority");
      if(this.priority=="LOW")
      {
        pill.className="badge rounded-pill bg-success myPill";
      }
      else if (this.priority=="MEDIUM")
      {
        pill.className="badge rounded-pill bg-warning myPill";
      }
      else if (this.priority=="HIGH")
      {
        pill.className="badge rounded-pill bg-danger myPill";
      }
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

}
.myModal
{
  margin-top: 2em;
  background: white;
  display: flex;
  justify-content: center;
  padding: 2.2em 16px 2.2em 16px;
  border-radius: 20px;
  max-width: 70%;
  min-width: 450px;
}
@media only screen and (max-width: 600px)
{
  .myModal
  {
    margin-top: 0em;
    width: 100%;
    height: 100%;
    border-radius: 0px;
    padding: 2.2em 16px 2.2em 16px;
  }
}

.myPills
{
  margin-top: 8px;
}
.myPill
{
  margin-right: 4px;
}
.keywordInput
{
  display: inline-block;
  width: 75%;
}
.addButton
{
  display: inline-block;
  margin-left: 7px;
  width: 23%;
}
.myLabel
{
  display: block;
}
.formTitle
{
  opacity: 50%;
}
.formTitleDiv
{
  text-align: center;
}
.centerCol
{
  display: flex;
  justify-content: center;
}
</style>
