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
    addTab("item-add","新增商品")
}

function closetab(title) {
    $("#tab").tabs("close",title);
}

function edit() {
}

function remove() {
    var selectRows = $('#tb').datagrid('getSelections');
    if (selectRows.length == 0) {
        $.messager.alert('提示', '未选中记录', 'warning');
        return;
    }
    $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
        if (r) {
            //获取用户选中的记录
            var ids = [];
            for (var i = 0; i < selectRows.length; i++) {
                ids.push(selectRows[i].id);
            }
            //异步提交给后台
            $.ajax({
                url: "items/batch",
                type: "post",
                data: {"ids[]": ids},
                success: function (data) {
                    if (data != null) {
                        $('#tb').datagrid('reload');
                    }
                },
                dataType: "json"
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
    $('#tb').datagrid('load', {
        title: $('#title').val(),
        status: $('#status').combobox('getValue')
    });
}
function addTab(href,text) {
    if($('#tab').tabs('exists',text)){
        $('#tab').tabs('select',text)
    }else {
        $('#tab').tabs('add',{
            title: text,
            href: href,
            closable:true
        });}
}
var ddshop = {
    registerMenuEvent: function () {
        var $tree = $('#menu .easyui-tree');
        //将当前树打印到控制台
        //console.log($tree);
        $tree.tree({
            onClick:function(node) {
                var href = node.attributes.href;//item-add
                var text = node.text;
//                debugger;
                addTab(href,text);
            }
        });

    },
    pagesize: function () {


        $('#tb').datagrid({
            url: 'items',
            //隔行变色，斑马线效果
            striped: true,
            //添加分页工具栏
            pagination: true,
            //每行的前面显示行号
            rownumbers: true,
            //使得数据表格自适应填充父容器
            fit: true,
            //默认显示10条，这样的话就显示20条
            pageSize: 20,
            //分页列表
            pageList: [20, 50, 100],
            //多列排序
            multiSort: true,
            //列属性
            columns: [[
                //field title width列属性
                {field: 'ck', checkbox: true},
                {field: 'id', title: '商品编号', width: 100, sortable: true},
                {field: 'title', title: '商品名称', width: 100, sortable: true},
                {field: 'catname', title: '商品类型', width: 100},
                {field: 'statusName', title: '商品状态', width: 100},
                {
                    field: 'price', title: '商品价格', formatter: function (value, row, index) {
                    var number = new Number(value / 100);
                    return number.toFixed(2);
                }
                },
                {
                    field: 'created', title: '创建时间', formatter: function (value, row, index) {
                    return moment(value).format("L");
                }
                },
                {
                    field: 'updated', title: '更新时间', formatter: function (value, row, index) {
                    return moment(value).format("L");
                }
                }
            ]],
            toolbar: "#toolbar"
        })
    }

};



