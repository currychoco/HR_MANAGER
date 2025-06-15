<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import Header from './layouts/Header.vue'
import Footer from './layouts/Footer.vue'
import { useRoute } from 'vue-router'

const route = useRoute()

</script>

<template>
    <Header v-if="route.meta.layout !== 'none'" />
    <RouterView />
    <Footer v-if="route.meta.layout !== 'none'" />
</template>

<style scoped>
</style>
