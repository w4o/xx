<template>
  <div class="layout-padding">
    <div style="height: 100%; padding: 20px; border-radius: 4px; background: #ffffff">
      <MediaLibrary v-model:value="mediaData" editModal/>
    </div>
  </div>
</template>

<script lang="ts" name="home" setup>
import {defineAsyncComponent, ref} from 'vue';

const mediaData = ref([] as any)
const MediaLibrary = defineAsyncComponent(() => import('/@/components/mediaLibrary/index.vue'));
</script>

<style lang="scss" scoped>
</style>
