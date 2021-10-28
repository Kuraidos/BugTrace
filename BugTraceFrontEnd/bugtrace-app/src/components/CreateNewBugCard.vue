<template>
  <div class="myBackground">
        <div class="centerCol">
          <div class="myModal">
            <div>
              <div class="formTitleDiv">
                <h5 class="formTitle">Bug Card Creation</h5>
              </div>
              <form>
                <div class="form-group myInputs">
                  <label for="email">Name of a Bug</label>
                  <input type="email" class="form-control" id="email"  placeholder="Crashing Zoom">
                </div>
                <div class="form-group myInputs">
                  <label for="password">Assign to </label>
                  <input type="password" class="form-control" id="password" placeholder="None">
                </div>
                <div class="form-group myInputs">
                  <label for="password">Priority</label>
                  <input type="password" class="form-control" id="password" placeholder="Set Priority">
                </div>
                <div class="form-group myInputs">
                  <label for="password" class="myLabel">Add Keywords</label>
                  <input type="password" class="form-control keywordInput" id="password" placeholder="Zoom">
                  <button type="button" class="btn btn-primary addButton">Add</button>
                  <div class="myPills">
                    <span class="badge rounded-pill bg-danger myPill">Danger</span>
                    <span class="badge rounded-pill bg-primary myPill">Primary</span>
                    <span class="badge rounded-pill bg-primary myPill">Primary</span>
                    <span class="badge rounded-pill bg-primary myPill">Primary</span>
                    <span class="badge rounded-pill bg-primary myPill">Primary</span>
                  </div>
                </div>
                <div class="form-group">
                  <label for="exampleFormControlTextarea1">Description</label>
                  <textarea class="form-control" id="exampleFormControlTextarea1" rows="8" style="resize: none"></textarea>
                </div>
                <button class="btn btn-primary myBigButton">Create</button>
              </form>
            </div>
          </div>
        </div>
  </div>
</template>

<script>
export default {
  name: "CreateNewBugCard"
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
