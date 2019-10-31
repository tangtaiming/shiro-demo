/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
    config.toolbarGroups = [
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'forms', groups: [ 'forms' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        { name: 'links', groups: [ 'links' ] },
        { name: 'insert', groups: [ 'insert' ] },
        { name: 'styles', groups: [ 'styles' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];

    config.removeButtons = 'Save,Print,NewPage,Cut,Copy,Paste,PasteText,PasteFromWord,Undo,Redo,Find,Replace,SelectAll,Scayt,Language,Iframe,Flash,About,BidiLtr,BidiRtl,Templates,Preview,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,TextColor,ShowBlocks,BGColor,Styles,Format,Font,FontSize,HorizontalRule,Smiley,SpecialChar,JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock,CreateDiv,Blockquote,Outdent,Indent,BulletedList,NumberedList,CopyFormatting,RemoveFormat,Italic,Underline,Strike,Subscript,Superscript,Anchor,Unlink,Link';
};
