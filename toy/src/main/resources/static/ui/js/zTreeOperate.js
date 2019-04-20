var zTreeOperate = {
    zTree: "",
    suburl:"",
    getselectedchild: function (tid) {
        var treeObj = this.zTree;
        return treeObj.getNodeByTId(tid);
    },
    getselectedNode: function () {
        var treeObj = this.zTree;
        return treeObj.getSelectedNodes();
    },
    getselecttreedata: function (searchCondition) {
        var highlightNodes = new Array();
        if (searchCondition != "") {
            var treeObj = this.zTree;
            highlightNodes = treeObj.getNodesByParam('flag', searchCondition, null);
        }

        return highlightNodes;
    },
    setFontCss_ztree: function (treeId, treeNode) {
        return (!!treeNode.highlight) ? { color: "#A60000", "font-weight": "bold" } : { color: "#333", "font-weight": "normal" };
    },
    expand_ztree: function () {
        var treeObj = this.zTree;
        treeObj.expandAll(true);
    },
    close_ztree: function () {
        var treeObj = this.zTree;
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i].id == '0') {
                //根节点：展开
                treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                treeObj.expandNode(nodes[i], false, true, false);
            }
        }
        treeObj.refresh();
    },
    search_ztree: function (searchConditionId, type) {
        this.searchByFlag_ztree(searchConditionId, "", type);
    },
    searchByFlag_ztree: function (searchConditionId, flag, type) {
        //<1>.搜索条件
        var searchCondition = searchConditionId;
        //<2>.得到模糊匹配搜索条件的节点数组集合
        var highlightNodes = new Array();
        if (searchCondition != "") {
            var treeObj = this.zTree;
            highlightNodes = treeObj.getNodesByParamFuzzy(type, searchCondition, null);
        }
        //<3>.高亮显示并展示【指定节点s】
        this.highlightAndExpand_ztree(highlightNodes, flag);
    },
    highlightAndExpand_ztree: function (highlightNodes, flag) {
        var treeObj = this.zTree;
        //<1>. 先把全部节点更新为普通样式
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
        //<2>.收起树, 只展开根节点下的一级节点
        this.close_ztree();
        //<3>.把指定节点的样式更新为高亮显示，并展开
        if (highlightNodes != null) {
            for (var i = 0; i < highlightNodes.length; i++) {
                if (flag != null && flag != "") {
                    if (highlightNodes[i].flag == flag) {
                        //高亮显示节点，并展开
                        highlightNodes[i].highlight = true;
                        treeObj.updateNode(highlightNodes[i]);
                        //高亮显示节点的父节点的父节点....直到根节点，并展示
                        var parentNode = highlightNodes[i].getParentNode();
                        var parentNodes = this.getParentNodes_ztree(parentNode);
                        treeObj.expandNode(parentNodes, true, false, true);
                        treeObj.expandNode(parentNode, true, false, true);
                    }
                } else {
                    //高亮显示节点，并展开
                    highlightNodes[i].highlight = true;
                    treeObj.updateNode(highlightNodes[i]);
                    //高亮显示节点的父节点的父节点....直到根节点，并展示
                    var parentNode = highlightNodes[i].getParentNode();
                    var parentNodes = this.getParentNodes_ztree(parentNode);
                    treeObj.expandNode(parentNodes, true, false, true);
                    treeObj.expandNode(parentNode, true, false, true);
                }
            }
        }
    },
    getParentNodes_ztree: function (node) {
        if (node != null) {
            var parentNode = node.getParentNode();
            return this.getParentNodes_ztree(parentNode);
        } else {
            return node;
        }
    },
    expandAll: function (treeObj) {
        this.zTree = treeObj;
        return treeObj;
    },
    setting: function () {
        var setting = {
            async: {
                enable: true,
                url: zTreeOperate.getsubUrl
            },
            view: {
                selectedMulti: false,
                fontCss: zTreeOperate.setFontCss_ztree
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: this.onTreeClick,
                onCheck: this.zTreeOnCheck,
                beforeExpand: this.beforeExpand,
                onAsyncSuccess: this.onAsyncSuccess
            }
        };
        return setting;
    },
    ajaxGetNodes: function (treeNode, reloadType) {
        var zTree = zTreeOperate.zTree;
        if (reloadType == "refresh") {
            treeNode.icon = "../../images/loading.gif";
            zTree.updateNode(treeNode);
        }
        zTree.reAsyncChildNodes(treeNode, reloadType, true);
    },
    /*getsubUrl: function (treeId, treeNode) {
        return zTreeOperate.suburl+"?pid=" + treeNode.id;
    },*/
    onAsyncSuccess: function (event, treeId, treeNode, msg) {
        if (!msg || msg.length == 0) {
            return;
        }
        var zTree = zTreeOperate.zTree;
      //  treeNode.icon = "";
        zTree.updateNode(treeNode);
        zTree.selectNode(treeNode.children[0]);
        if ($('#txtVehicleSearch').val() != '') {
            zTreeOperate.searchByFlag_ztree($('#txtVehicleSearch').val(), 3, 'title');
            $('#txtVehicleSearch').val('');
        }
    },
    beforeExpand: function (treeId, treeNode) {
        if (treeNode.flag != '2') return;
        if (!treeNode.isAjaxing) {
            zTreeOperate.ajaxGetNodes(treeNode, "refresh");
            return true;
        } else {
            alert("zTree 正在下载数据中，请稍后展开节点。。。");
            return false;
        }
    },
    init: function (url) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            success: function (data) {
                zTreeOperate.expandAll($.fn.zTree.init($("#treeDemo"), zTreeOperate.setting(), data.rows));
            }
        });
    },
    zTreeOnCheck: function zTreeOnCheck(event, treeId, treeNode) {
        var treeObj = zTreeOperate.zTree;
        var treeNodes = treeObj.getCheckedNodes();
    },
    onTreeClick: function (event, treeId, treeNode) {

    }
}