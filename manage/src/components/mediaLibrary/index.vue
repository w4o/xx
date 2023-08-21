<template>
  <div style="height: 100%; display: flex; flex-direction: row">
    <div style="min-width: 180px; height: 100%;">
      <el-button size="small" style="width: 180px" type="primary" @click="handleAddCategory">添加分类</el-button>

      <div class="isTree">
        <el-tree :data="categoryTree" :props="{label:'name',value:'id'}" class="mt2"
                 style="width: 100%" @node-click="handleNodeClick">
          <template #default="{ node, data }">
            <span class="mr6" @click="handleNodeClick(data)">{{ node.label }}</span>
            <el-icon
                v-if="currentCategory.id === data.id && currentCategory.id !== 0 && currentCategory.id !== null"
                color="#79bbff"
                @click.stop="handleEditCategory(data)">
              <ele-EditPen/>
            </el-icon>
          </template>
        </el-tree>
      </div>
    </div>
    <div
        style="padding: 0 10px 0 10px; width: 100%; display: flex; flex-direction: column; justify-content: space-between;">
      <div
          style="display: flex; flex-direction: row; flex-wrap: wrap;">
        <div
            style="width: 100px; height: 100px; margin-bottom: 5px; margin-right: 5px; display: flex; justify-content: center; align-items: center; border: 1px dashed var(--el-border-color); border-radius: 6px;">
          <el-upload
              :http-request="uploadRequest"
              :show-file-list="false"
              limit="1"
              list-type="picture"
          >
            <el-icon color="#dedfe0" size="55">
              <ele-Plus/>
            </el-icon>
          </el-upload>
        </div>
        <div v-for="(item,index) in mediaData.records" :key="index"
             style="position: relative; width: 100px; height: 100px; margin-bottom: 5px; margin-right: 5px; border-radius: 6px; border: 1px solid var(--el-border-color); overflow: hidden;">
          <div v-if="currentMedia.includes(item)" class="angle_mark">
            <span>✓</span>
          </div>
          <img :alt="item.description" :src="item.url"
               height="98" style="border-radius: 6px;"
               width="98" @click="handleClickMedia(item)">
        </div>
      </div>
      <div style="display: flex; justify-content: end">
        <el-pagination
            v-model:current-page="mediaParams.page"
            v-model:page-size="mediaParams.pageSize"
            :total="mediaData.total"
            layout="prev, pager, next, jumper"
            small="small"
        />
      </div>
    </div>
    <div v-if="editModal && currentMedia.length > 0"
         style="min-width: 250px; width: 250px; background: #f7f7f7; border-radius: 4px">
      <EditMedia :categoryTree="editModalCategoryTree" :media="currentMedia[0]" @refreshMedia="getMedia"/>
    </div>
  </div>

  <CategoryDialog ref="categoryDialogRef" :categoryTree="editModalCategoryTree" @refreshCategoryTree="getCategoryTree"/>

</template>

<script lang="ts" name="mediaLibraryIndex" setup>
import {defineAsyncComponent, onMounted, ref, watch} from "vue";
import {mediaCategoryApi} from "/@/api/system/mediaCategory";
import {mediaApi} from "/@/api/system/media";
import {ElLoading} from "element-plus";
import {uploadApi} from "/@/api/upload";

const props = defineProps({
  multiple: {
    type: Boolean,
    default: false
  },
  value: {
    type: [Object, Array],
    default: () => {
      return []
    }
  },
  editModal: {
    type: Boolean,
    default: false
  },
  defaultCheckedIds: {
    type: Array,
    default: () => {
      return []
    }
  }
})

const CategoryDialog = defineAsyncComponent(() => import('/@/components/mediaLibrary/categoryDialog.vue'));
const EditMedia = defineAsyncComponent(() => import('/@/components/mediaLibrary/editMedia.vue'));

const MediaCategoryApi = mediaCategoryApi();
const MediaApi = mediaApi();
const UploadApi = uploadApi();
const emit = defineEmits(['update:value'])

const categoryDialogRef = ref(null)

const categoryTree = ref([] as any)
const editModalCategoryTree = ref([] as any)
const currentCategory = ref({} as any)
const mediaData = ref([] as any)
const currentMedia = ref([] as any)

const mediaParams = ref({
  page: 1,
  pageSize: 10,
  categoryId: null
})

const handleAddCategory = () => {
  categoryDialogRef.value?.showCategory(currentCategory.value, 'add')
}

const handleEditCategory = (data: any) => {
  categoryDialogRef.value?.showCategory(data, 'edit')
}

const getCategoryTree = () => {
  categoryTree.value = [
    {name: '全部', id: null},
    {name: '默认', id: 0}
  ]
  MediaCategoryApi.getMediaCategory().then(res => {
    if (res) {
      categoryTree.value = categoryTree.value.concat(res)
      editModalCategoryTree.value = res
    }
  })
}

const handleNodeClick = (data: any) => {
  currentCategory.value = data
  mediaParams.value.categoryId = data.id
  getMedia()
}

const getMedia = () => {
  currentMedia.value = []
  const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
  MediaApi.getMedia(mediaParams.value).then(res => {
    mediaData.value = res
    defaultChecked()
  }).finally(() => {
    loading.close()
  })
}

const uploadRequest = (res: any) => {
  const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
  const data = {
    file: res.file,
    categoryId: currentCategory.value.id
  }
  UploadApi.uploadImage(data).then(() => {
    getMedia()
  }).finally(() => {
    loading.close()
  })
}

const handleClickMedia = (data: any) => {
  if (props.editModal) {
    if (currentMedia.value.includes(data)) {
      currentMedia.value = []
    } else {
      currentMedia.value = [data]
    }
  } else {
    if (props.multiple) {
      if (currentMedia.value.includes(data)) {
        currentMedia.value.splice(currentMedia.value.indexOf(data), 1)
      } else {
        currentMedia.value.push(data)
      }
      emit('update:value', currentMedia.value)
    } else {
      if (currentMedia.value.includes(data)) {
        currentMedia.value = []
      } else {
        currentMedia.value = [data]
      }
      emit('update:value', currentMedia.value[0])
    }
  }
}

const defaultChecked = () => {
  if (props.defaultCheckedIds) {
    props.defaultCheckedIds.forEach(id => {
      currentMedia.value.push(mediaData.value.records.find((item: any) => item.mediaId === id))
    })
  }
}

watch(() => props.defaultCheckedIds, () => {
  defaultChecked()
  emit('update:value', currentMedia.value)
})

onMounted(async () => {
  getMedia()
  getCategoryTree()
})
</script>

<style lang="scss">
.isTree {
  overflow-x: auto;
  max-width: 180px;
  height: 98%;
  padding: 0 10px 0 10px;

  .el-tree > .el-tree-node {
    display: inline-block;
    min-width: 100%;
  }
}

.angle_mark {
  position: absolute;
  top: -25px;
  right: -25px;
  background-color: #529b2e;
  width: 50px;
  height: 50px;
  transform: rotate(45deg);
  // 角标文字
  span {
    position: absolute;
    display: inline-block;
    color: #fff;
    width: 100%;
    bottom: 0;
    left: 0;
    text-align: center;
    transform: rotate(-45deg);
  }
}
</style>
