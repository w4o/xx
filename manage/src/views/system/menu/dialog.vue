<template>
  <div class="system-menu-dialog-container">
    <el-dialog v-model="state.dialog.isShowDialog" :title="state.dialog.title" width="769px">
      <el-form ref="menuDialogFormRef" :model="state.ruleForm" :rules="state.rules" label-width="80px"
               size="default">
        <el-row :gutter="35">
          <el-col :lg="24" :md="24" :sm="24" :xl="24" :xs="24" class="mb20">
            <el-form-item label="上级菜单" prop="parentId">
              <el-cascader
                  v-model="state.ruleForm.parentId"
                  :options="state.menuData"
                  :props="{ checkStrictly: true, emitPath:false, value: 'id', label: 'title' }"
                  class="w100"
                  clearable
                  placeholder="请选择上级菜单"
              >
                <template #default="{ node, data }">
                  <span>{{ data.title }}</span>
                  <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
                </template>
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24" :xl="24" :xs="24" class="mb20">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="state.ruleForm.menuType">
                <el-radio label="menu">菜单</el-radio>
                <el-radio label="btn">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
            <el-form-item :label="state.ruleForm.menuType === 'menu'?'菜单名称':'按钮名称'" prop="title">
              <el-input v-model="state.ruleForm.title" clearable
                        placeholder="格式：message.router.xxx"></el-input>
            </el-form-item>
          </el-col>
          <template v-if="state.ruleForm.menuType === 'menu'">
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="路由名称" prop="name">
                <el-input v-model="state.ruleForm.name" clearable
                          placeholder="路由中的 name 值"></el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="路由路径" prop="path">
                <el-input v-model="state.ruleForm.path" clearable
                          placeholder="路由中的 path 值"></el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="重定向" prop="redirect">
                <el-input v-model="state.ruleForm.redirect" clearable
                          placeholder="请输入路由重定向"></el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="菜单图标" prop="icon">
                <IconSelector v-model="state.ruleForm.icon" placeholder="请输入菜单图标"/>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="组件路径" prop="component">
                <el-input v-model="state.ruleForm.component" clearable
                          placeholder="组件路径"></el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="链接地址" prop="link">
                <el-input
                    v-model="state.ruleForm.link"
                    :disabled="!state.ruleForm.isLink"
                    clearable
                    placeholder="外链/内嵌时链接地址（http:xxx.com）"
                >
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="权限标识" prop="roles">
                <el-select v-model="state.ruleForm.roles" class="w100" clearable multiple
                           placeholder="取角色管理">
                  <el-option label="admin" value="admin"></el-option>
                  <el-option label="common" value="common"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </template>
          <template v-if="state.ruleForm.menuType === 'btn'">
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="权限标识" prop="btnPower">
                <el-input v-model="state.ruleForm.btnPower" clearable
                          placeholder="请输入权限标识"></el-input>
              </el-form-item>
            </el-col>
          </template>
          <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
            <el-form-item label="菜单排序" prop="sort">
              <el-input-number v-model="state.ruleForm.sort" class="w100"
                               controls-position="right" placeholder="请输入排序"/>
            </el-form-item>
          </el-col>
          <template v-if="state.ruleForm.menuType === 'menu'">
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="是否隐藏" prop="isHide">
                <el-radio-group v-model="state.ruleForm.isHide">
                  <el-radio :label="true">隐藏</el-radio>
                  <el-radio :label="false">不隐藏</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="页面缓存" prop="isKeepAlive">
                <el-radio-group v-model="state.ruleForm.isKeepAlive">
                  <el-radio :label="true">缓存</el-radio>
                  <el-radio :label="false">不缓存</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="是否固定" prop="isAffix">
                <el-radio-group v-model="state.ruleForm.isAffix">
                  <el-radio :label="true">固定</el-radio>
                  <el-radio :label="false">不固定</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="是否外链" prop="isLink">
                <el-radio-group v-model="state.ruleForm.isLink" :disabled="state.ruleForm.isIframe">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="24" class="mb20">
              <el-form-item label="是否内嵌" prop="isIframe">
                <el-radio-group v-model="state.ruleForm.isIframe" @change="onSelectIframeChange">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </template>
        </el-row>
      </el-form>
      <template #footer>
				<span class="dialog-footer">
					<el-button size="default" @click="onCancel">取 消</el-button>
					<el-button size="default" type="primary" @click="onSubmit">{{ state.dialog.submitTxt }}</el-button>
				</span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" name="systemMenuDialog" setup>
import {defineAsyncComponent, nextTick, onMounted, reactive, ref} from 'vue';
import {storeToRefs} from 'pinia';
import {useRoutesList} from '/@/stores/routesList';
import {i18n} from '/@/i18n';
import {useMenuApi} from "/@/api/system/menu";
import {setBackEndControlRefreshRoutes} from "/@/router/backEnd";

// 定义子组件向父组件传值/事件
const emit = defineEmits(['refresh']);

// 引入组件
const IconSelector = defineAsyncComponent(() => import('/@/components/iconSelector/index.vue'));

const menuApi = useMenuApi();

// 定义变量内容
const menuDialogFormRef = ref();
const stores = useRoutesList();
const {routesList} = storeToRefs(stores);
const state = reactive({
  // 参数请参考 `/src/router/route.ts` 中的 `dynamicRoutes` 路由菜单格式
  ruleForm: {
    parentId: '', // 上级菜单
    menuType: 'menu', // 菜单类型
    name: '', // 路由名称
    component: '', // 组件路径
    isLink: false, // 是否外链
    sort: 0, // 菜单排序
    path: '', // 路由路径
    redirect: '', // 路由重定向，有子集 children 时
    title: '', // 菜单名称
    icon: '', // 菜单图标
    isHide: false, // 是否隐藏
    isKeepAlive: true, // 是否缓存
    isAffix: false, // 是否固定
    link: '', // 外链/内嵌时链接地址（http:xxx.com），开启外链条件，`1、isLink: 链接地址不为空`
    isIframe: false, // 是否内嵌，开启条件，`1、isIframe:true 2、isLink：链接地址不为空`
    roles: '', // 权限标识，取角色管理
    btnPower: '', // 菜单类型为按钮时，权限标识
  },
  menuData: [] as RouteItems, // 上级菜单数据
  dialog: {
    isShowDialog: false,
    type: '',
    title: '',
    submitTxt: '',
  },
  rules: {
    name: [{required: true, message: '请输入路由名称', trigger: 'blur'}],
    component: [{required: true, message: '请输入组件路径', trigger: 'blur'}],
    path: [{required: true, message: '请输入路由路径', trigger: 'blur'}],
    title: [{required: true, message: '请输入菜单名称', trigger: 'blur'}],
  }
});

// 获取 pinia 中的路由
const getMenuData = (routes: RouteItems) => {
  const arr: RouteItems = [];
  routes.map((val: RouteItem) => {
    val['title'] = i18n.global.t(val.meta?.title as string);
    arr.push({...val});
    if (val.children) getMenuData(val.children);
  });
  return arr;
};
// 打开弹窗
const openDialog = (type: string, row?: any) => {
  if (type === 'edit') {
    // 模拟数据，实际请走接口
    row.menuType = 'menu';
    row.sort = Math.floor(Math.random() * 100);
    state.dialog.title = '修改菜单';
    state.dialog.submitTxt = '修 改';
    nextTick(() => {
      state.ruleForm = {...row};
      menuDialogFormRef.value.clearValidate();
    });
  } else {
    state.dialog.title = '新增菜单';
    state.dialog.submitTxt = '新 增';
    // 清空表单，此项需加表单验证才能使用
    nextTick(() => {
      menuDialogFormRef.value.resetFields();
      if (row) {
        state.ruleForm.parentId = row.id;
      } else {
        state.ruleForm.parentId = "0"
      }
    });
  }
  state.dialog.type = type;
  state.dialog.isShowDialog = true;
};
// 关闭弹窗
const closeDialog = () => {
  state.dialog.isShowDialog = false;
};
// 是否内嵌下拉改变
const onSelectIframeChange = () => {
  state.ruleForm.isLink = !!state.ruleForm.isIframe;
};
// 取消
const onCancel = () => {
  closeDialog();
};
// 提交
const onSubmit = async () => {
  menuDialogFormRef.value.validate(async (valid: boolean) => {
    if (!valid) return;

    if (state.dialog.type === 'edit')
      await menuApi.editMenu(state.ruleForm).then(() => {
        // location.reload();
      })
    else if (state.dialog.type === 'add')
      await menuApi.addMenu(state.ruleForm).then(() => {
        // location.reload();
      });

    emit('refresh');
    closeDialog(); // 关闭弹窗
    await setBackEndControlRefreshRoutes() // 刷新菜单，未进行后端接口测试
  });

};
// 页面加载时
onMounted(() => {
  state.menuData = getMenuData(routesList.value);
});

// 暴露变量
defineExpose({
  openDialog,
});
</script>
