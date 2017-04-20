// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.ArrayList;

final class MyJSONParser implements JSONParser {

  @Override
  public JSON parse(String in) throws IOException {
    MyJSON json = null;
    char current;
    boolean inStr = false;
    boolean isName = true;
    StringBuilder string = new StringBuilder();
    String name = "";
    for (int i = 0; i < in.length(); i++) {
      current = in.charAt(i);
      if (current == '{') {
         if (json == null)
            json = new MyJSON();
         else {
            json.setObject(name.toString(), parse(in.substring(i, in.lastIndexOf('}') + 1)));
            i = in.lastIndexOf('}') - 1;
         }
      }
      else if (current == '\"') {
         if (inStr) {
            if (isName) {
               name = string.toString();
            }
            else {
               json.setString(name, string.toString());
            }
            string = new StringBuilder();
         }
         inStr = !inStr;
      }
      else if (current == ':' || current == ',') {
         isName = !isName;
      }
      else if (current == '}') {
         Collection<String> col = new ArrayList<String>();
         json.getStrings(col);
         return json;
      }
      else {
         if (inStr)
            string.append(current);
      }
    }
    return json;
  }
}
