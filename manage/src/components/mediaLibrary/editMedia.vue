<template>
  <div
      style="display: flex; align-items: center; justify-content: space-between; padding: 10px; flex-direction: column; height: 100%;">
    <div>
      <img :src="form.url" alt="" style="width: 100%">
      <el-form ref="formRef"
               :model="form"
               size="default"
               style="margin-top: 10px">
        <el-form-item label="分类" prop="parentId">
          <el-cascader
              v-model="form.mediaCategoryId"
              :options="categoryTree"
              :props="{ checkStrictly: true, emitPath:false, label:'name',value:'id' }"
              class="w100"
              clearable
              placeholder="请选择上级菜单"
          >
            <template #default="{ node, data }">
              <span>{{ data.name }}</span>
              <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
            </template>
          </el-cascader>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" clearable/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" autosize type="textarea"/>
        </el-form-item>
      </el-form>
    </div>

    <div style="display: flex; flex-direction: column; width: 100%">
      <div>
        <el-button style="width: 100%" type="danger" @click="handleDelete">删除</el-button>
      </div>
      <div style="margin-top: 10px">
        <el-button style="width: 100%" type="primary" @click="handleConfirm">确认</el-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref, watch} from "vue";
import {mediaApi} from "/@/api/system/media";
import {ElLoading, ElMessage, ElMessageBox} from 'element-plus'

const props = defineProps({
  categoryTree: {
    type: Array,
    default: () => {
      return []
    }
  },
  media: {
    type: Object,
    default: () => {
      return {}
    }
  }
})

const emit = defineEmits(['refreshMedia'])
const MediaApi = mediaApi()

const formRef = ref()
const form = ref({
  mediaCategoryId: '',
} as any)

const handleConfirm = () => {
  const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
  if (!form.value.mediaCategoryId) {
    form.value.mediaCategoryId = '0'
  }
  MediaApi.updateMedia(form.value).then(() => {
    ElMessage.success('修改成功.')
    emit('refreshMedia')
  }).finally(() => {
    loading.close()
  })
}

const handleDelete = () => {
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
    MediaApi.deleteMedia(form.value.mediaId).then(() => {
      loading.close()
      emit('refreshMedia')
    }).finally(() => {
      loading.close()
    })
  }).catch(() => {
  });
}

watch(() => props.media, (val) => {
  if (val) {
    const {mediaId, mediaCategoryId, url, title, description} = val
    form.value = {mediaId, mediaCategoryId, url, title, description}
  }
})

onMounted(() => {
  const {mediaId, mediaCategoryId, url, title, description} = props.media
  form.value = {mediaId, mediaCategoryId, url, title, description}
})
</script>

<style lang="scss" scoped>

</style>
