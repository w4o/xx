<template>
  <el-dialog
      v-model="showDialog"
      :title="title"
      destroy-on-close
  >
    <MediaLibrary v-model:value="mediaData" :multiple="multiple"/>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" name="mediaLibraryDialog" setup>
import {defineAsyncComponent, ref} from "vue";

const MediaLibrary = defineAsyncComponent(() => import('/@/components/mediaLibrary/index.vue'));

const props = defineProps({
  value: {
    type: [Array, Object],
    default: () => {
      return {}
    }
  },
  title: {
    type: String,
    default: '选择图片'
  },
  multiple: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['update:value'])

const showDialog = ref(false)
const mediaData = ref([] as any)

const show = () => {
  showDialog.value = true
}

const handleConfirm = () => {
  emit('update:value', mediaData.value)
  showDialog.value = false
}

// 暴露变量
defineExpose({
  show,
});
</script>

<style lang="scss" scoped>

</style>
