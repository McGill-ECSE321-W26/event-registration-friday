<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/events">Events</RouterLink>
    </nav>
  </header>
  <RouterView />
</template>

<style scoped>
nav {
  display: flex;
  gap: 24px;
  padding: 12px 24px;
  background: #000;
}

nav a {
  color: #aaa;
}

nav a:hover {
  color: #fff;
}
</style>
