<template>
  <el-dialog
      v-model="show"
      :title="`${title}分类`"
      width="30%"
  >
    <el-form ref="formRef" :model="form" :rules="rules" label-width="80px"
             size="default">
      <el-form-item label="分类" prop="parentId">
        <el-cascader
            v-model="form.parentId"
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
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="form.name"/>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="show = false">取消</el-button>
        <el-button v-if="title === '编辑'" type="danger" @click="handleDelete">删除</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" name="mediaLibraryCategoryDialog" setup>
import {nextTick, ref} from "vue";
import {mediaCategoryApi} from "/@/api/system/mediaCategory";
import {ElLoading, ElMessageBox} from 'element-plus'

const props = defineProps({
  categoryTree: {
    type: Array,
    default: () => {
      return []
    }
  }
})

const emit = defineEmits(['refreshCategoryTree'])
const MediaCategoryApi = mediaCategoryApi()

const formRef = ref()
const form = ref({parentId: '', name: '', id: null})
const rules = ref({
  name: [
    {required: true, message: '请输入分类名称', trigger: ['blur', 'input']}
  ]
})

const show = ref(false)
const title = ref('添加')

const showCategory = (row, type) => {
  if (type === 'edit') {
    title.value = '编辑'
    nextTick(() => {
      form.value = {...row}
      formRef.value.clearValidate();
    });
  } else {
    title.value = '添加'
    form.value.name = ''
    nextTick(() => {
      form.value = {
        parentId: '',
        name: '',
        id: null
      }
      if (row && row.id) {
        form.value.parentId = row.id
      }
    });
  }
  show.value = true
}

const handleConfirm = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
      if (!form.value.parentId) {
        form.value.parentId = '0'
      }
      if (title.value === '编辑') {
        MediaCategoryApi.updateMediaCategory(form.value).finally(() => {
          emit('refreshCategoryTree')
          loading.close()
        })
      } else {
        MediaCategoryApi.mediaCategory(form.value).finally(() => {
          emit('refreshCategoryTree')
          loading.close()
        })
      }
      show.value = false
    }
  })
}

const handleDelete = () => {
  ElMessageBox.confirm('此操作将永久删除该分类, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
    MediaCategoryApi.deleteMediaCategory(form.value.id).finally(() => {
      emit('refreshCategoryTree')
      loading.close()
    })
    show.value = false
  }).catch(() => {
  });
}

// 暴露变量
defineExpose({
  showCategory,
});
</script>

<style lang="scss" scoped>

</style>
