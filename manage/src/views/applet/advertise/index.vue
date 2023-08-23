<template>
  <div class="system-dic-container layout-padding">
    <el-card class="layout-padding-auto" shadow="hover">
      <div style="display: flex; flex-direction: row; flex-wrap: wrap;">
        <div v-for="(item,index) in adData" :key="index"
             style="border-radius: 5px; border: 1px solid #dedfe0; margin-bottom: 10px; margin-right: 10px; display: flex; flex-direction: column; padding: 10px"
             @click="toConfig(item)">
          <span style="font-weight: 500">标题: {{ item.title }}</span>
          <span>位置名称: {{ item.position }}</span>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts" name="appletAdvertise" setup>
import {useRouter} from "vue-router";

const router = useRouter();

const adData = [
  {title: '首页顶部轮播图', position: 'homeTop'},
  {title: '首页简历', position: 'homeResume'},
]

const toConfig = (item: any) => {
  router.push({
    name: 'appletAdConfig',
    query: item
  })
}
</script>

<style lang="scss" scoped>
</style>
