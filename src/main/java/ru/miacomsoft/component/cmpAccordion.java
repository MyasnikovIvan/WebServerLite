package ru.miacomsoft.component;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.miacomsoft.WebServerLite.ServerResourceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class cmpAccordion extends Base {
    /*

<cmpAccordion label="выбор" name="ctrlAcordion" onchange="console.log(D3Api.getValue('ctrlAcordion'));"  heightstyle="auto">
<cmpAccordion label="выбор" name="ctrlAcordion" onchange="console.log(D3Api.getValue('ctrlAcordion'));" height="30%" width="50%" left="30%" top="100px">
    <items caption="text1">
            111111111111
        <cmpLabel caption="test CTRL"/>
    </items>
    <items caption="text2">
        <cmpEdit name="v2_edit" label=" текст в другой вкладке -edit2--"/>
    </items>
    <items caption="text3">
         афывафывафывафывафывафыв
    </items>
</cmpAccordion>

     */
    public cmpAccordion(Document doc, Element element) {
        super(doc, element, "span");
        this.initCmpType(element);
        this.initCmpId(element);
        Attributes attrs = element.attributes();
        Attributes attrsDst = this.attributes();
        attrsDst.add("schema", "accordion");
        attrsDst.add("type", "accordion");
        String width = getCssArrKeyRemuve(attrs, "width", true);
        String height = getCssArrKeyRemuve(attrs, "height", true);
        String left = getCssArrKeyRemuve(attrs, "left", true);
        String top = getCssArrKeyRemuve(attrs, "top", true);

        String heightStyle = getCssArrKeyRemuve(attrs, "heightStyle", true);
        if (height.length() > 0 && heightStyle.length() == 0) {
            heightStyle = "heightStyle: \"fill\" ";
        }

        // ============== INIT Html Class =========================
        List<String> classCSS = new ArrayList<>();
        if (attrs.hasKey("class")) {
            classCSS.addAll(Arrays.asList(RemoveArrKeyRtrn(attrs, "class").split(" ")));
        }
        attrsDst.add("class", String.join(" ", classCSS));
        // ---------------------------------------------------------

        // ===============INIT Html style================
        List<String> style = new ArrayList<>();
        if (attrs.hasKey("style")) {
            style.addAll(Arrays.asList(RemoveArrKeyRtrn(attrs, "style").split(";")));
        }
        // attrsDst.add("style", String.join(";", style));
        // переписать
        attrsDst.add("style", "position: relative;" + width + height + left + top);
        // ==============================================

        String ctrlName = this.attr("name") + "_ctrl";
        String name = this.attr("name");
        attrsDst.add("ctrl", ctrlName);

        StringBuffer sb = new StringBuffer();
        sb.append("<script> $(function() {");
        sb.append(" D3Api.setControlAuto('"); sb.append(name); sb.append("', $('[name=\""); sb.append(name); sb.append("\"]'));");
        sb.append("$('[name=\""); sb.append(ctrlName); sb.append("\"]').accordion({"); sb.append(heightStyle); sb.append("});");
        if (attrs.hasKey("onchange")) {
            sb.append("$('[name=\""); sb.append(ctrlName); sb.append("\"]').on('accordionactivate', function(event, ui) {\n");
            sb.append(RemoveArrKeyRtrn(attrs, "onchange", "")); sb.append(";");
            sb.append("});\n");
        }
        ;
        sb.append(getJQueryEventString(ctrlName, attrs, true));
        sb.append("}); </script>");
        sb.append("<div name=\""); sb.append(ctrlName); sb.append("\" style=\"position: relative;"); sb.append(height); sb.append(width); sb.append(left); sb.append(top); sb.append("\">\n");
        int childrenint = 0;
        for (int numChildNode = 0; numChildNode < element.childNodeSize(); numChildNode++) {
            // System.out.println(numChildNode + ")  " + element.childNode(numChildNode).nodeName() + " element.childrenSize() " + element.childrenSize() + " numChildNode " + numChildNode);
            if (element.childNode(numChildNode).nodeName().equals("#text")) {

            } else if (element.childNode(numChildNode).nodeName().equals("#comment")) {

            } else if (element.childNode(numChildNode).nodeName().equals("#cdata")) {

            } else {
                if (childrenint < element.childrenSize()) {
                    Element itemElement = element.child(childrenint);
                    Attributes attrsItem = itemElement.attributes();
                    String path = RemoveArrKeyRtrn(attrsItem, "path", "");
                    String htmlText = "";
                    if (path.length() == 0) {

                        ServerResourceHandler.parseElementV2(doc, element.child(childrenint), element);
                        htmlText = element.child(childrenint).toString();
                    } else {
                        String query_selector = RemoveArrKeyRtrn(attrsItem, "query_selector", "");
                        htmlText = ServerResourceHandler.parseSubElement(doc, path, query_selector);
                    }
                    sb.append("  <h3 block=\"label\" value=\""); sb.append(childrenint); sb.append( "\">");
                    sb.append(RemoveArrKeyRtrn(attrsItem, "caption", ""));
                    sb.append("  </h3>");
                    sb.append("  <div>\n");
                    sb.append(htmlText);
                    sb.append("  </div>\n");
                    childrenint++;
                }
            }
        }
        sb.append("</div>");
        sb.append(cmpPopup.initPopUpCtrl(doc, attrs, name)); // добавление контекстного меню на контрол
        this.append(sb.toString());
    }
}
