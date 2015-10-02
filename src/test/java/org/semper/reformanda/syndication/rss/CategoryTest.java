/**
 * Copyright 2015 Joshua Cain
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semper.reformanda.syndication.rss;

import org.custommonkey.xmlunit.XpathEngine;
import org.junit.Before;
import org.junit.Test;
import org.semper.reformanda.syndication.rss.itunes.Category;
import org.semper.reformanda.syndication.rss.itunes.ItunesCategory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private Rss rss;

    @Before
    public void setUp() {
        rss = new Rss();

        final Channel channel = new Channel();
        rss.setChannel(channel);
    }

    @Test
    public void shouldIncludeCategoryTextAsXmlAttribute() throws Exception {
        final String categoryName = ItunesCategory.Business.value();
        final Category category = new Category();
        category.setText(categoryName);
        rss.getChannel().setCategory(category);

        final Document document = XmlUtils.getDocument(rss);
        final XpathEngine engine = XmlUtils.getXpathEngine();

        NodeList matchingNodes = engine.getMatchingNodes(String.format("/rss/channel/itunes:category[@text='%s']", categoryName), document);
        assertEquals("Could not find itunes category attribute", 1, matchingNodes.getLength());
    }

    @Test
    public void shouldNextSubcategoriesAsElementsUnderParentCategory() throws Exception {
        final Category category = new Category();
        category.setText(ItunesCategory.Business.value());
        final Category subCategory = new Category();
        subCategory.setText(ItunesCategory.Business.careers);
        category.getSubcategories().add(subCategory);
        rss.getChannel().setCategory(category);

        final Document document = XmlUtils.getDocument(rss);
        final XpathEngine engine = XmlUtils.getXpathEngine();

        final String subcategoryXpath = String.format("/rss/channel/itunes:category[@text='%s']/itunes:category[@text='%s']", ItunesCategory.Business.value(), ItunesCategory.Business.careers);
        NodeList matchingNodes = engine.getMatchingNodes(subcategoryXpath, document);
        assertEquals("Could not find itunes subcategory attribute", 1, matchingNodes.getLength());
    }
}