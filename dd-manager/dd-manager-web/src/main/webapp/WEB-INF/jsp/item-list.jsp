<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox" >
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>
<table id="tb"></table>

<script src="js/common.js"></script>
<script>
        ddshop.pagesize();
        function up() {
            var selectRows = $('#tb').datagrid('getSelections');
            if (selectRows.length == 0) {
                $.messager.alert('提示', '未选中记录', 'warning');
                return;
            }
            $.messager.confirm('确认', '您确认想要上架商品吗？', function (r) {
                if (r) {
                    //获取用户选中的记录
                    var ids = [];
                    for (var i = 0; i < selectRows.length; i++) {
                        ids.push(selectRows[i].id);
                    }
                    //异步提交给后台
                    $.ajax({
                        url: "items/up",
                        type: "post",
                        data: {"ids[]": ids},
                        success: function (data) {
                            if (data != null) {
                                $('#tb').datagrid('reload');
                            }
                        },
                        dataType: "json"
                    })
                }
            })
        }
        function add() {
        }
        function edit() {
        }
        function remove() {
            var selectRows = $('#tb').datagrid('getSelections');
            if(selectRows.length == 0){
                $.messager.alert('提示','未选中记录','warning');
                return;
            }
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    //获取用户选中的记录
                    var ids = [];
                    for(var i=0;i< selectRows.length;i++){
                        ids.push(selectRows[i].id);
                    }
                    //异步提交给后台
                    $.ajax({
                        url:"items/batch",
                        type:"post",
                        data:{"ids[]":ids},
                        success:function(data){
                            if(data!=null){
                                $('#tb').datagrid('reload');
                            }
                        },
                        dataType:"json"
                    });

                }
            });
        }
        function down() {
            var selectRows = $('#tb').datagrid('getSelections');
            if (selectRows.length == 0) {
                $.messager.alert('提示', '未选中记录', 'warning');
                return;
            }
            $.messager.confirm('确认', '您确认想要下架商品吗？', function (r) {
                if (r) {
                    //获取用户选中的记录
                    var ids = [];
                    for (var i = 0; i < selectRows.length; i++) {
                        ids.push(selectRows[i].id);
                    }
                    //异步提交给后台
                    $.post(
                        "items/down",
                        {"ids[]": ids},
                        function (data) {
                            if (data != null) {
                                $('#tb').datagrid('reload');
                            }
                        },
                        "json"
                    )

                }
            });
        }
        function searchForm() {
            $('#tb').datagrid('load',{
                title: $('#title').val(),
                status: $('#status').combobox('getValue')
            });
        }

</script>

