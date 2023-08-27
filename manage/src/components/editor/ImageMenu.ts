import {IButtonMenu, IDomEditor} from "@wangeditor/editor";

class ImageMenu implements IButtonMenu {
    tag: string;
    title: string;
    iconSvg: string;

    constructor() {
        this.title = '插入图片'; // 鼠标悬浮显示
        this.tag = 'button'
        this.iconSvg = '<svg viewBox="0 0 1024 1024"><path d="M959.877 128l0.123 0.123v767.775l-0.123 0.122H64.102l-0.122-0.122V128.123l0.122-0.123h895.775zM960 64H64C28.795 64 0 92.795 0 128v768c0 35.205 28.795 64 64 64h896c35.205 0 64-28.795 64-64V128c0-35.205-28.795-64-64-64zM832 288.01c0 53.023-42.988 96.01-96.01 96.01s-96.01-42.987-96.01-96.01S682.967 192 735.99 192 832 234.988 832 288.01zM896 832H128V704l224.01-384 256 320h64l224.01-192z"></path></svg>'; // 菜单图标
    }

    isActive(editor: IDomEditor): boolean { // 保持默认
        return false
    }

    getValue(editor: IDomEditor): string | boolean { // 保持默认
        return ''
    }

    isDisabled(editor: IDomEditor): boolean { // 保持默认
        return false;
    }

    exec(editor: IDomEditor, value: string | boolean) { // 菜单点击事件，这里将点击事件暴露出去
        if (this.isDisabled(editor)) {
            return;
        }
        editor.emit('ImageMenuClick');
    }

}

export default ImageMenu
