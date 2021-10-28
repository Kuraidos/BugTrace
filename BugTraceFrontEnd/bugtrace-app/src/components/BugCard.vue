<template>
  <div class="card myCard">
    <div class="card-body">
      <h5 class="card-title">Crashing Zoom</h5>
      <div class="myDate"><p class="card-text"><small class="text-muted">3 days ago</small></p></div>
      <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: Klaidas Serelis</h6>
      <div class="myPills">
        <span class="badge rounded-pill bg-danger myPill">Danger</span>
        <span class="badge rounded-pill bg-primary myPill">Primary</span>
        <span class="badge rounded-pill bg-primary myPill">Primary</span>
        <span class="badge rounded-pill bg-primary myPill">Primary</span>
        <span class="badge rounded-pill bg-primary myPill">Primary</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BugCard"
}
</script>

<style scoped>

.myPill
{
  margin-right: 4px;
}
.myDate
{
  position: absolute;
  top: 8px;
  right: 8px;
}
</style>
