<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格(元)：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">商品描述</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>
<script>
    var ue = UE.getEditor('container',{
        initialFrameWidth: '100%',
        initialFrameHeight: '400'
    });
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
   function submitForm() {
        $("#itemAddForm").form('submit',{
            url:"itemadd",
            onSubmit:function () {
                $("#price").val($("#priceView").val()*100);
                return $(this).form('validate');
            },
            success:function (data) {
                if(data>0){
                    $.messager.alert('恭喜', '添加成功！');
                    addTab("item-list","查询商品")
                }
            }
        })
   }
    function clearForm(){
        $("#itemAddForm").form("reset");
        ue.setContent('商品描述');
    }

</script>