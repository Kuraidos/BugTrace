<template>
  <div class="myBackground" @click.self="addedEvent()">
        <div @click.self="addedEvent" class="centerCol">
          <div class="myModal">
            <div>
              <div class="formTitleDiv">
                <h5 class="formTitle">Bug Card Creation</h5>
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
                  <label for="priority">Priority</label>
                  <input v-model="priority" type="text" class="form-control" id="priority" placeholder="Set Priority">
                </div>
                <div class="form-group myInputs">
                  <label for="keywords" class="myLabel" >Add Keywords</label>
                  <input v-model="tempKeyword" @keyup="addKeyword" type="text" class="form-control keywordInput" id="keywords" placeholder="Zoom">
                  <button type="button" class="btn btn-primary addButton">Add</button>
                  <div class="myPills">
                    <span class="badge rounded-pill bg-danger myPill">{{ priority }}</span>
                    <span v-for="keyword in keywords " class="badge rounded-pill bg-primary myPill">{{ keyword }}</span>
                  </div>
                </div>
                <div class="form-group">
                  <label>Description</label>
                  <textarea v-model="description" class="form-control" id="descriptio" rows="8" style="resize: none"></textarea>
                </div>
                <button @click="create()" class="btn btn-primary myBigButton">Create</button>
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
        title:"",assignTo:"",priority:"",keywords:[],description:"",tempKeyword:""
    }},
  methods: {
    create()
    {
      console.log(this.title)
      console.log(this.assignTo)
      console.log(this.priority)
      console.log(this.keywords)
      console.log(this.description)
      let self = this;
      axios.post('http://localhost:8080/app/create', {email: this.$route.params.email, password: this.$route.params.password,username: this.$route.params.username,teamId: this.$route.params.teamId,
      title: this.title,assignTo:this.assignTo,priority:this.priority,keywords:this.keywords,description:this.description}).then(function (response) {
      });
      this.addedEvent()
    },
    addKeyword(e)
    {
      console.log(e)
      if((e.key==='Enter' || e.key===',') && this.tempKeyword!="")
      {
          this.keywords.push(this.tempKeyword.replaceAll(",",""))
          this.tempKeyword=""
      }

    }
    ,
    addedEvent()
    {
      this.$emit("added")
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
