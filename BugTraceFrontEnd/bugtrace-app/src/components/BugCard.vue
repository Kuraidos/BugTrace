<template>
  <div class="card myCard">
    <div class="card-body">
      <h5 class="card-title">{{card.title}}</h5>
      <div class="myDate"><p class="card-text"><small class="text-muted">{{ card.dateCreated }}</small></p></div>
      <h6 class="card-subtitle mb-2 text-muted" style="font-size: 0.8em">Created by: {{ card.creator}}</h6>
      <div class="myPills">
        <span class="badge rounded-pill bg-danger myPill">{{ card.impact }}</span>
        <span v-for="keyword in card.keywords" :key="keyword.keyword" class="badge rounded-pill bg-primary myPill">{{ keyword.keyword }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BugCard",
  props:['card']
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
