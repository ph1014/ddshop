<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true">

    <form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">规格参数：</td>
                <td>
                    <button class="easyui-linkbutton" onclick="addGroup()" type="button" data-options="iconCls:'icon-add'">添加分组</button>
                    <ul id="item-param-group">

                    </ul>
                    <ul id="item-param-group-template" style="display:none;">
                        <li>
                            <input name="group">
                            <button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button" data-options="iconCls:'icon-add'"></button>
                            <button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                            <ul class="item-param">
                                <li>
                                    <input name="param">
                                    <button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                                </li>

                            </ul>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="easyui-linkbutton" onclick="submitFormparam()" type="button" data-options="iconCls:'icon-ok'">保存</button>
                    <button class="easyui-linkbutton" onclick="clearForm()" type="button" data-options="iconCls:'icon-undo'">重置</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="js/common.js"></script>
<script>
    $("#cid").combotree({
        url:'itemCat?parentid=0',
        required:true,
        onBeforeExpand:function (node) {
            var $tree = $("#cid").combotree("tree");
            var option = $tree.tree("options");
            option.url="itemCat?parentid="+node.id;
        },
        onBeforeSelect:function (node) {
            var isLeaf = $("#cid").tree("isLeaf",node.target);
            if(!isLeaf){
                $.messager.alert('警告', '请选中最终的类别！', 'warning');
                return false;
            }
        }
    })

    function addGroup(){
        $li = $("#item-param-group-template li").eq(0).clone();
        $("#item-param-group").append($li);
    }

    function addParam(ele) {
        $li = $("#item-param-group-template .item-param li").eq(0).clone();
        $(ele).parent().find(".item-param").append($li);
    }
    function delGroup(ele){
        $(ele).parent().remove();
    }
    function delParam(ele){
        $(ele).parent().remove();
    }
    function submitFormparam(){
        var groupValues=[];
        var $group =$("#item-param-group [name=group]");

        $.each($group,function (i,e) {
            var $param = $(e).parent().find(".item-param [name=param]");
            var params=[];
            $.each($param,function (i,e){
                $_val = $(e).val();
                if($.trim($_val).length>0){
                    params.push($_val);
                }
            })
            var o={};
            var op =$(e).val();
            o.group=op;
            o.param=params;
            if($.trim(op).length>0 && params.length>0){
                groupValues.push(o);
            }


        })
        var itemCatId = $("#cid").combotree("getValue");
        var jsonStr = JSON.stringify(groupValues);
        var url ="itemparamsave?itemCatId="+itemCatId;
       $.post(
            url,
           {paramData:jsonStr},
           function (data) {
                if(data>0){
                    closetab("新增商品规格模板");
                    closetab("规格参数");
                    addTab("item-params-list","规格参数");
                }

           }
       )

    }

</script>