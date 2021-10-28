<template>
  <div class="container">
  <div class="row">
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">To do</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
    </div>
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">In Progress</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
    </div>
    <div class="col-sm">
      <div class="myColDiv"><h5 class="myColName">Completed</h5></div>
      <div class="addBugCard"><h1 class="display-5 addSymbol">+</h1></div>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
      <BugCard></BugCard>
    </div>
  </div>
  </div>
  <CreateNewBugCard></CreateNewBugCard>
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
