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

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

final class MyJSON implements JSON {

   private HashMap<String, Object> map;
   private String string;

   public MyJSON () {
      map = new HashMap<String, Object>();
   }

   public MyJSON (String str) {
      map = new HashMap<String, Object>();
      string = str;
   }

  @Override
  public JSON getObject(String name) {
   Object value = map.get(name);
   if (value instanceof JSON)
    return (JSON)value;
   return null;
  }

  @Override
  public JSON setObject(String name, JSON value) {
    map.put(name, value);
    return this;
  }

  @Override
  public String getString(String name) {
   Object value = map.get(name);
   if (value instanceof String)
    return (String)value;
   return null;
  }

  @Override
  public JSON setString(String name, String value) {
    map.put(name, value);
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    Set<Map.Entry<String, Object>> set = map.entrySet();
    for (Map.Entry<String, Object> entry : set) {
      if (entry.getValue() instanceof JSON)
         names.add(entry.getKey());
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    Set<Map.Entry<String, Object>> set = map.entrySet();
    for (Map.Entry<String, Object> entry : set) {
      if (entry.getValue() instanceof String)
         names.add(entry.getKey());
    }
  }
}
