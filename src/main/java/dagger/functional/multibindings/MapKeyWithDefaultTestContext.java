/*
 * Copyright (C) 2018 The Dagger Authors.
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

package dagger.functional.multibindings;

import com.google.auto.value.AutoAnnotation;
import dagger.Component;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import java.lang.annotation.Retention;
import java.util.Map;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public final class MapKeyWithDefaultTestContext {
  @Retention(RUNTIME)
  @MapKey(unwrapValue = false)
  @interface MapKeyWithDefault {
    boolean hasDefault() default true;
    boolean required();
  }

  @Module
  interface TestModule {
    @Provides
    @IntoMap
    @MapKeyWithDefault(required = false)
    static int justRequired() {
      return 1;
    }

    @Provides
    @IntoMap
    @MapKeyWithDefault(required = false, hasDefault = false)
    static int both() {
      return 2;
    }
  }

  @Component(modules = TestModule.class)
  interface TestComponent {
    Map<MapKeyWithDefault, Integer> map();
  }

  @AutoAnnotation
  static MapKeyWithDefault mapKey(boolean hasDefault, boolean required) {
    return new AutoAnnotation_MapKeyWithDefaultTestContext_mapKey(hasDefault, required);
  }
}
