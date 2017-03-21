(function() {
  cola(function(model) {
    model.dataType({
      name: "Product",
      properties: {
        productName: {
          validators: ["required"]
        },
        id: {
          dataType: "number"
        },
        categoryId: {
          dataType: "number"
        },
        quantityPerUnit: {
          dataType: "number"
        },
        unitPrice: {
          dataType: "number"
        },
        unitsInStock: {
          dataType: "number"
        },
        unitsOnOrder: {
          dataType: "number"
        }
      }
    });
    model.dataType({
      name: "Category",
      properties: {
        categoryName: {
          validators: ["required"]
        },
        products: {
          dataType: "Product",
          provider: {
            url: "/service/product/",
            pageSize: 5,
            beforeSend: function(self, arg) {
              arg.options.data.categoryId = arg.model.get("categorys.id");
              return NProgress.start();
            },
            complete: function(sefl, arg) {
              return NProgress.done();
            }
          }
        }
      }
    });
    model.describe("categorys", {
      dataType: "Category",
      provider: {
        url: "/service/category/",
        pageSize: 5
      }
    });
    model.describe("customProduct", "Product");
    model.describe("customCategory", "Category");
    model.describe("products", []);
    model.action({
      addCategory: function() {
        model.set("customCategory", {});
        return cola.widget("categoryLayer").show();
      },
      addProduct: function() {
        var current;
        current = model.get("categorys").current;
        model.set("customProduct", {
          categoryId: current.get("id")
        });
        return cola.widget("productLayer").show();
      },
      productCancel: function() {
        model.set("customProduct", {});
        return cola.widget("productLayer").hide();
      },
      productSave: function() {
        var data, product, result;
        product = model.get("customProduct");
        result = product.validate();
        if (result) {
          data = product.toJSON();
          NProgress.start();
          return $.ajax("./service/product/", {
            data: JSON.stringify(data),
            type: data.id ? "PUT" : "POST",
            contentType: "application/json",
            complete: function() {
              model.get("categorys").current.get("products").flush();
              cola.widget("productLayer").hide();
              return NProgress.done();
            }
          });
        }
      },
      categoryCancel: function() {
        model.set("customCategory", {});
        return cola.widget("categoryLayer").hide();
      },
      categorySave: function() {
        var category, data, result;
        category = model.get("customCategory");
        result = category.validate();
        if (result) {
          data = category.toJSON();
          console.log(data);
          NProgress.start();
          return $.ajax("./service/category/", {
            data: JSON.stringify(data),
            type: data.id ? "PUT" : "POST",
            contentType: "application/json",
            complete: function() {
              cola.widget("categoryLayer").hide();
              return NProgress.done();
            }
          });
        }
      }
    });
    model.widgetConfig({
      productLayer: {
        $type: "Layer"
      },
      categoryLayer: {
        $type: "Layer"
      },
      categoryList: {
        $type: "table",
        bind: "item in categorys",
        showHeader: true,
        columns: [
          {
            bind: ".id",
            caption: "分类编号"
          }, {
            bind: ".categoryName",
            caption: "分类名"
          }, {
            bind: ".description",
            caption: "描述信息"
          }
        ],
        currentPageOnly: true,
        autoLoadPage: false,
        changeCurrentItem: true,
        highlightCurrentItem: true
      },
      productList: {
        $type: "table",
        bind: "item in categorys.products",
        showHeader: true,
        columns: [
          {
            bind: ".id",
            caption: "编号"
          }, {
            bind: ".productName",
            caption: "产品名称"
          }, {
            bind: ".quantityPerUnit",
            caption: "规格"
          }, {
            bind: ".unitPrice",
            caption: "单价"
          }, {
            bind: ".unitsInStock",
            caption: "库存"
          }, {
            bind: ".unitsOnOrder",
            caption: "订单量"
          }
        ]
      },
      productPager: {
        $type: "pager",
        bind: "categorys.products"
      },
      categoryPager: {
        $type: "pager",
        bind: "categorys"
      }
    });
    return window.cModel = model;
  });

}).call(this);
