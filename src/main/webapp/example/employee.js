(function () {
    cola(function (model) {
        $(".ui.accordion").accordion({
            exclusive: false
        });
        model.describe("employees", {
            dataType: {
                name: "Employee",
                properties: {
                    name: {
                        validators: [
                            "required", {
                                $type: "length",
                                min: 1,
                                max: 20
                            }
                        ]
                    },
                    email: {
                        validators: [
                            "required", {
                                $type: "length",
                                min: 4,
                                max: 20
                            }
                        ]
                    },
                    gender: {
                        dataType: "boolean",
                        defaultValue: true,
                        validators: ["required"]
                    },
                    birthday: {
                        dataType: "date"
                    },
                    createdAt: {
                        dataType: "date"
                    },
                    updatedAt: {
                        dataType: "date"
                    }
                }
            },
            provider: {
                url: "./service/employee/find",
                pageSize: 10,
                beforeSend: function (self, arg) {
                    var contain, data;
                    data = arg.options.data;
                    contain = model.get("contain");
                    return data.contain = contain;
                }
            }
        });
        model.set("countries", [
            {
                name: "中国"
            }, {
                name: "美国"
            }, {
                name: "加拿大"
            }, {
                name: "印度尼西亚"
            }, {
                name: "马来西亚"
            }, {
                name: "英国"
            }, {
                name: "韩国"
            }, {
                name: "蒙古国"
            }, {
                name: "俄罗斯"
            }
        ]);
        model.describe("editItem", "Employee");
        model.action({
            getColor: function (status) {
                if (status === "完成") {
                    return "positive-text";
                } else {
                    return "negative-text";
                }
            },
            search: function () {
                return model.get("employees").flush();
            },
            add: function () {
                model.set("editItem", {
                    sex: true
                });
                return cola.widget("editLayer").show();
            },
            edit: function (item) {
                model.set("editItem", item.toJSON());
                return cola.widget("editLayer").show();
            },
            cancel: function () {
                return cola.widget("editLayer").hide();
            },
            ok: function () {
                debugger;
                var data, editItem, validate;
                editItem = model.get("editItem");
                validate = editItem.validate();
                if (validate) {
                    data = editItem.toJSON();
                    return $.ajax("./service/employee/", {
                        data: JSON.stringify(data),
                        type: data.id ? "PUT" : "POST",
                        contentType: "application/json",
                        complete: function () {
                            cola.widget("editLayer").hide();
                        }
                    });
                }
            },
            del: function (item) {
                item.remove();
                return $.ajax("./service/employee/"+item.get("id")+"/", {
                    type: "DELETE"
                });
            }
        });
        return model.widgetConfig({
            radioGroup: {
                items: [
                    {
                        value: true,
                        label: "男"
                    }, {
                        value: false,
                        label: "女"
                    }
                ]
            },
            editLayer: {
                $type: "layer",
                width: "100%",
                onShow: function () {
                    return $("#mainView").hide();
                },
                beforeHide: function () {
                    return $("#mainView").show();
                }
            },
            shopDropDown: {
                $type: "dropdown",
                "class": "error",
                openMode: "drop",
                items: "{{shop in shops}}",
                valueProperty: "name",
                bind: "editItem.shop"
            },
            birthDatePicker: {
                $type: "datePicker",
                bind: "editItem.birthday",
                displayFormat: "yyyy-MM-dd HH:mm:ss"
            },
            hireDatePicker: {
                $type: "datePicker",
                bind: "editItem.createdAt",
                displayFormat: "yyyy-MM-dd HH:mm:ss"
            },
            countryDropDown: {
                $type: "dropdown",
                "class": "error",
                openMode: "drop",
                items: "{{country in countries}}",
                valueProperty: "name",
                bind: "editItem.country"
            },
            employeeTable: {
                $type: "table",
                showHeader: true,
                bind: "item in employees",
                highlightCurrentItem: true,
                currentPageOnly: true,
                columns: [
                    {
                        bind: ".id",
                        caption: "ID"
                    }, {
                        bind: ".name",
                        caption: "Name"
                    }, {
                        bind: ".avatar",
                        caption: "AVATAR"
                    }, {
                        bind: ".degree",
                        caption: "DEGREE"
                    }, {
                        bind: ".website",
                        caption: "WEBSITE"
                    }, {
                        bind: "item.gender?'男':'女'",
                        caption: "GENDER"
                    }, {
                        bind: "formatDate(item.birthday, 'yyyy-MM-dd HH:mm:ss')",
                        caption: "Birthday"
                    }, {
                        bind: ".email",
                        caption: "EMAIL"
                    }, {
                        caption: "Operation",
                        align: "center",
                        template: "operations"
                    }
                ]
            }
        });
    });

}).call(this);
