# mvp-sample
一个MVP的例子，当然稍微和传统上常见的MVP结构不一样，不然就不会分享到Github了。  

现在Activity/Fragment不再作为View了，而是作为Presenter，View单独提出去，Moduel也是单独提出去的。这样做的好处是扩展了Presenter的能力和灵活性，也就是说控制器的能力也附加给Presenter了（比如把Activity/Fragment的能力附加给它），一个功能的业务逻辑完全由Presenter来掌控。  

在Demo中提供了三种常见的形式的例子，还有一个Fragment的用法的例子，下载代码看看吧，这里不啰嗦了。

## License
```text
Copyright 2018 Yan Zhenjie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```