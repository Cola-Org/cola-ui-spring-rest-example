(function() {
  var App, e, getAjaxID, language, properties, rootApp, rootWindow, startedAjaxList, title, win;

  win = window.parent;

  while (win) {
    try {
      if (win.App) {
        rootApp = win.App;
        rootWindow = win;
        break;
      }
      if (win === win.parent) {
        break;
      }
      win = win.parent;
    } catch (_error) {
      e = _error;
    }
  }

  if (!rootApp) {
    properties = {
      mainView: "./frame/main",
      loginPath: "./login",
      longPollingTimeout: 0,
      longPollingInterval: 2000,
      "service.messagePull": "./service/message/pull",
      "service.login": "./service/account/login",
      "service.logout": "./service/account/logout",
      "service.menus": "./service/menus",
      "service.user.detail": "./service/user/detail",
      title: "Cola-Frame"
    };
  }

  App = window.App = {
    _tabs: {},
    getRootWindow: function() {
      if (rootApp) {
        return rootWindow;
      } else {
        return window;
      }
    },
    open: function(path, config) {
      var tab, viewTab;
      if (rootApp) {
        rootApp.open.apply(path, config);
      } else {
        viewTab = cola.widget("viewTab");
        if (this._tabs[path]) {
          tab = viewTab.getTab(path);
          viewTab.setCurrentTab(tab);
        } else {
          if (!config.type || config.type === "subWindow") {
            tab = new cola.TabButton({
              afterClose: (function(_this) {
                return function(self, arg) {
                  return _this.close(self.get("name"));
                };
              })(this),
              content: {
                $type: "iFrame",
                path: config.path
              },
              icon: config.icon,
              name: path,
              closeable: config.closeable || true,
              caption: config.label
            });
            viewTab = cola.widget("viewTab");
            this._tabs[path] = tab;
            viewTab.addTab(tab);
            return viewTab.setCurrentTab(tab);
          } else {
            window.open(path);
          }
        }
      }
    },
    close: function(path) {
      return delete this._tabs[path];
    },
    goLogin: function(callback) {
      if (rootApp) {
        return rootApp.goLogin(callback);
      } else {
        return login(callback);
      }
    },
    setTitle: function(title) {
      if (rootApp) {
        return rootApp.setTitle(path);
      } else {
        return document.title = title;
      }
    },
    setFavicon: function(path) {
      var i, icon, len, ref, rel, results;
      if (rootApp) {
        return rootApp.setFavicon(path, config);
      } else {
        ref = ["icon", "shortcut icon"];
        results = [];
        for (i = 0, len = ref.length; i < len; i++) {
          rel = ref[i];
          icon = $("link[rel='" + rel + "']");
          if (icon.length > 0) {
            results.push(icon.attr("href", path));
          } else {
            results.push(document.head.appendChild($.xCreate({
              tagName: "link",
              rel: "icon",
              href: path
            })));
          }
        }
        return results;
      }
    },
    refreshMessage: function() {
      if (rootApp) {
        return rootApp.refreshMessage();
      } else {
        return typeof refreshMessage === "function" ? refreshMessage() : void 0;
      }
    },
    prop: function(key, value) {
      var i, len, p, results;
      if (rootApp) {
        return rootApp.prop(key, value);
      } else {
        if (arguments.length === 1) {
          if (typeof key === "string") {
            return properties[key];
          } else if (key) {
            results = [];
            for (i = 0, len = key.length; i < len; i++) {
              p = key[i];
              if (key.hasOwnProperty(p)) {
                results.push(properties[p] = key[p]);
              } else {
                results.push(void 0);
              }
            }
            return results;
          }
        } else {
          return properties[key] = value;
        }
      }
    }
  };

  title = App.prop("title");

  if (title) {
    App.setTitle(title);
  }

  cola.defaultAction("setting", function(key) {
    return App.prop(key);
  });

  cola.defaultAction("numberString", function(number) {
    return ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen"][number - 1];
  });

  $(document).ajaxError(function(event, jqXHR) {
    var message;
    if (jqXHR.status === 401) {
      App.goLogin();
      return false;
    } else {
      message = jqXHR.responseJSON;
      if (message) {
        throw new cola.Exception(message);
      }
    }
  });

  language = $.cookie("_language") || window.navigator.language;

  if (language) {
    document.write("<script src=\"resources/cola-ui/i18n/" + language + "/cola.js\"></script>");
    document.write("<script src=\"resources/i18n/" + language + "/common.js\"></script>");
  }

  $(NProgress.done);

  getAjaxID = function(event) {
    var id, key, value;
    id = "";
    for (key in event) {
      value = event[key];
      if (key.indexOf("jQuery") === 0) {
        id = key;
        break;
      }
    }
    if (id) {
      if (!(parseInt(id.replace("jQuery", "")) > 0)) {
        id = "";
      }
    }
    return id;
  };

  startedAjaxList = [];

  $(document).ajaxStart(function(event) {
    var id;
    id = getAjaxID(event);
    startedAjaxList.push(id);
    if (!NProgress.isStarted()) {
      return NProgress.start();
    }
  });

  $(document).ajaxComplete(function(event) {
    var id, index;
    id = getAjaxID(event);
    index = startedAjaxList.indexOf(id);
    if (index > -1) {
      startedAjaxList.splice(index, 1);
    }
    if (startedAjaxList.length === 0) {
      return NProgress.done();
    }
  });

}).call(this);
