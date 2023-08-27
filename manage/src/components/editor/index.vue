<template>
  <div class="editor-container">
    <Toolbar :defaultConfig="toolbarConfig" :editor="editorRef" :mode="mode"/>
    <Editor
        v-model="state.editorVal"
        :defaultConfig="state.editorConfig"
        :mode="mode"
        :style="{ height }"
        @onChange="handleChange"
        @onCreated="handleCreated"
    />
    <MediaLibraryDialog ref="mediaRef" v-model:value="mediaData"/>
  </div>
</template>

<script lang="ts" name="wngEditor" setup>
// https://www.wangeditor.com/v5/for-frame.html#vue3
import '@wangeditor/editor/dist/css/style.css';
import {onBeforeUnmount, reactive, ref, shallowRef, watch} from 'vue';
import {Boot, IDomEditor, IToolbarConfig} from '@wangeditor/editor';
import {Editor, Toolbar} from '@wangeditor/editor-for-vue';
import ImageMenu from "/@/components/editor/ImageMenu";
import MediaLibraryDialog from "/@/components/mediaLibrary/mediaLibraryDialog.vue";

// 定义父组件传过来的值
const props = defineProps({
  // 是否禁用
  disable: {
    type: Boolean,
    default: () => false,
  },
  // 内容框默认 placeholder
  placeholder: {
    type: String,
    default: () => '请输入内容...',
  },
  // https://www.wangeditor.com/v5/getting-started.html#mode-%E6%A8%A1%E5%BC%8F
  // 模式，可选 <default|simple>，默认 default
  mode: {
    type: String,
    default: () => 'default',
  },
  // 高度
  height: {
    type: String,
    default: () => '310px',
  },
  // 双向绑定，用于获取 editor.getHtml()
  getHtml: String,
  // 双向绑定，用于获取 editor.getText()
  getText: String,
});

// 定义子组件向父组件传值/事件
const emit = defineEmits(['update:getHtml', 'update:getText']);

// 定义变量内容
const editorRef = shallowRef();
const mediaRef = ref();
const mediaData = ref([])
const state = reactive({
  editorConfig: {
    placeholder: props.placeholder,
  },
  editorVal: props.getHtml,
});

// 编辑器内容改变时
const handleChange = (editor: IDomEditor) => {
  emit('update:getHtml', editor.getHtml());
  emit('update:getText', editor.getText());
};
// 页面销毁时
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) return;
  editor.destroy();
});
// 监听是否禁用改变
// https://gitee.com/lyt-top/vue-next-admin/issues/I4LM7I
watch(
    () => props.disable,
    (bool) => {
      const editor = editorRef.value;
      if (editor == null) return;
      bool ? editor.disable() : editor.enable();
    },
    {
      deep: true,
    }
);
// 监听双向绑定值改变，用于回显
watch(
    () => props.getHtml,
    (val) => {
      state.editorVal = val;
    },
    {
      deep: true,
    }
);

watch(mediaData, (val: any) => {
  editorRef.value.insertNode({
    type: 'image',
    src: val?.url,
    children: [{ // 该字段必须要
      text: ''
    }]
  })
})

const toolbarConfig: Partial<IToolbarConfig> = {
  excludeKeys: [
    "group-image", // 图片上传：本地上传+网络图片
    "group-video", // 视频上传：本地上传+网络视频
  ]
}

const MenusList = [
  {
    key: 'ImageMenu',
    class: ImageMenu,
    index: 23,
  }
]

const registerMenu = function (editor: IDomEditor, toolbarConfig: Partial<IToolbarConfig>) {
  const allRegisterMenu = editor.getAllMenuKeys(); // 获取所有已注册的菜单
  let keys = [];
  for (let item of MenusList) {
    if (allRegisterMenu.indexOf(item.key) < 0) { // 如果未注册，则注册
      const menuObj = {
        key: item.key,
        factory() {
          return new item.class()
        }
      }
      Boot.registerMenu(menuObj);
    }
    keys.push(item.key)
  }
  toolbarConfig.insertKeys = {
    index: MenusList[0].index,
    keys: keys
  }
}


// 编辑器回调函数
const handleCreated = (editor: IDomEditor) => {
  editorRef.value = editor;
  registerMenu(editorRef.value, toolbarConfig); // 注册自定义菜单。这个是5.4那边声明的
  initMediaMenuEvent(); // 注册自定义菜单点击事件
};

// 事件监听
const initMediaMenuEvent = () => {
  const editor = editorRef.value;
  // 在点击事件中，根据具体菜单，可以触发响应的功能，这里可以为每个事件创建一个el-dialog弹窗。我们就可以完全按照自己的需求开发后续功能
  editor.on('ImageMenuClick', () => {
    // 你点击了图片菜单
    mediaRef.value.show();
  });
}
</script>
