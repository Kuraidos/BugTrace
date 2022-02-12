<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

    <a style="margin: 0 40px" class="navbar-brand" href="#">Bugtrace</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div style="padding-left: 16px" class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Pasikiskepusteliaubamasis Team
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="#">Google's Team</a>
            <a class="dropdown-item" href="#">Pasikiskepusteliaubamasis Team</a>
            <a class="dropdown-item" href="#">Dat dis team</a>
          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Profile</a>
        </li>
      </ul>
    </div>

  </nav>

</template>

<script>
export default {
  name: "navBar"
}
</script>

<style scoped>
.myNav
{
  margin: 0 16px;
}
</style>
