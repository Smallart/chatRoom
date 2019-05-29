
  var Emotion = {
      _lastRange: null,
      setEdit: function(id) {
        Emotion.edit = document.getElementById(id);
      },
      getSelection: function () {
        return document.selection ? document.selection : window.getSelection()
      },
      getRange: function (c) {
        var sel = Emotion.getSelection();
        if (!sel) {
          return null
        }
        var range = sel.getRangeAt ? sel.rangeCount ? sel.getRangeAt(0) : null : sel.createRange();
        return range ;
      },
      saveRange: function () {
        Emotion._lastRange = Emotion.getRange();
      },
      resotreRange: function () {
        var lastRange = Emotion._lastRange;
        if (lastRange) {
          var sel = Emotion.getSelection();
          if (sel.addRange) {
            sel.removeAllRanges(), sel.addRange(lastRange)
          } else {
            var sel = Emotion.getRange();
            sel.setEndPoint("EndToEnd", lastRange);
            sel.setEndPoint("StartToStart", lastRange);
            sel.select();
          }
        }
        return this
      },
      focus: function (c) {
        // console.log('Emotion.edit: ', Emotion.edit);
        Emotion.edit.focus();
        var lastRange = Emotion._lastRange; //var b;
        if (c && lastRange) {
          var sel = Emotion.getSelection(); //d
          if (sel.addRange) {
            sel.removeAllRanges();
            sel.addRange(lastRange)
          } else {
            var range = Emotion.getRange(); //a
            range.setEndPoint("EndToEnd", lastRange);
            range.setEndPoint("StartToStart", lastRange);
            range.select()
          }
        }
        return Emotion.resotreRange()
      },
      insertHTML: function (d) {
        Emotion.focus(1);
        var range = Emotion.getRange(); //b

        // console.log('range: ', range);
        if (range.createContextualFragment) {
            d += '<img style="width:1px;height:1px;" >';
            var f = range.createContextualFragment(d),
                a = f.lastChild;
            range.deleteContents();
            range.insertNode(f);
            range.setEndAfter(a);
            range.setStartAfter(a);
            var sel = Emotion.getSelection(); //c
            sel.removeAllRanges();
            sel.addRange(range);
            document.execCommand("Delete", !1, null)
        } else {
            range.pasteHTML(d);
            range.collapse(!1);
            range.select()
        }
        Emotion.saveRange();
        return this
      }


    };

    // return Emotion;
