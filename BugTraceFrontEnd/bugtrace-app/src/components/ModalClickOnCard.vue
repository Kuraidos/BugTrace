<template>

  <div class="myBackground">
    <div class="container">
    <div class="row">
      <div class="col-3"></div>
      <div class="col-6">
        <div class="myModal">
          <h5 class="card-title">Crashing Zoom</h5>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: Klaidas Serelis<br>
              Created at: 11/11/2011
            </h6>
          </div>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: Klaidas Serelis<br>
              Created at: 11/11/2011
            </h6>
          </div>
          <div class="myAuthorAndData">
            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: Klaidas Serelis<br>
              Created at: 11/11/2011
            </h6>
          </div>
          <div class="myDescription">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis corporis cumque dolor, ex iste itaque iure libero magnam mollitia natus neque omnis, praesentium quaerat quisquam suscipit. Alias aperiam architecto, aspernatur commodinatus neque omnis, praesentium quaerat quisquam suscipit. Alias aperiam architecto, aspernatur commodi debitis deleniti deserunt dicta eveniet hic illum laudantium magnam nemo nulla numquam officia, possimus quidem similique soluta soluta soluta aa, veniam vitae.</p>
          </div>
          <div class="myPills">
            <span class="badge rounded-pill bg-danger myPill">Danger</span>
            <span class="badge rounded-pill bg-primary myPill">Primary</span>
            <span class="badge rounded-pill bg-primary myPill">Primary</span>
            <span class="badge rounded-pill bg-primary myPill">Primary</span>
            <span class="badge rounded-pill bg-primary myPill">Primary</span>
          </div>
          <div class="btn-group float-sm-end">
            <button type="button" class="btn btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Action
            </button>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">Complete</a>
              <a class="dropdown-item" href="#">Edit</a>
              <a class="dropdown-item" href="#">Remove</a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
  </div>


</template>

<script>
export default {
  name: "ModalClickOnCard"
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
  background: white;
  margin-top: 6em;
  padding: 16px 16px 50px 16px;
  border-radius: 20px;
}
.myAuthorAndData
{
  display: inline-block;
  margin-right: 16px;
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

</style>
