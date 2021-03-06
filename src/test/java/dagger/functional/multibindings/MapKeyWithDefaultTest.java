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

import static com.google.common.truth.Truth.assertThat;
import static dagger.functional.multibindings.MapKeyWithDefaultTestContext.mapKey;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.google.auto.value.AutoAnnotation;
import dagger.Component;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import java.lang.annotation.Retention;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class MapKeyWithDefaultTest {

  @Test
  public void test() {
    Map<MapKeyWithDefaultTestContext.MapKeyWithDefault, Integer> map = DaggerMapKeyWithDefaultTestContext_TestComponent.create().map();
    assertThat(map).hasSize(2);
    assertThat(map.get(mapKey(true, false))).isEqualTo(1);
    assertThat(map.get(mapKey(false, false))).isEqualTo(2);
  }
}
