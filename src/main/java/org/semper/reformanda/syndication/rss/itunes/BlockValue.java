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

package org.semper.reformanda.syndication.rss.itunes;

public enum BlockValue {
    YES("yes"),
    NO("no");

    private final String textRepresentation;

    private BlockValue(final String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    /**
     * @param blockValueText input text
     * @return <code>YES</code> if the input string matches the text value for <code>YES</code>.  Otherwise <code>NO</code>.
     */
    public static BlockValue fromText(final String blockValueText) {
        return YES.toString().equals(blockValueText) ? YES : NO;
    }

    @Override
    public String toString() {
        return textRepresentation;
    }
}