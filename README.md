# マルチプロジェクト設定の手順に関して

本プロジェクトはマルチプロジェクトの練習として作成されたものです。
「app」と「domain」という2つのプロジェクトで構成されています。
パッケージ構成は以下の図のとおりです 。

```
crawler/
 ├ app/
 │  ├ src/
 │  │  └ main/
 │  │     ├ java/
 │  │     │  └ com.example.app/　...　クローラWEBアプリのベースパッケージ
 │  │     │     └ AppApplication (読み込むプロジェクト追記が必要)
 │  │     └ resources/
 │  └ build.gradle
 │
 ├ core/
 │  ├ src/
 │  │  └ main/
 │  │     ├ java/
 │  │     │  └ com.exaple.core/　...　クローラーで使用するドメイン部分に関するモデルベースパッケージ
 │  │     └ resources/
 │  └ build.gradle
 │
 ├ gradlew
 ├ gradlew.bat
 ├ settings.gradle
 └ README.md　...　当ファイル
```

# 2つのプロジェクトの依存関係について 
「app」は「domain」プロジェクトに依存する形式で構成されている。
appプロジェクトの「build.gradle」ファイルに implementsの記述を追加することで実現しています。
この記述によりappがビルドされる時、domainプロジェクトもビルドされる仕組みになっています。

なお、gradleの動作はMavenと異なり、domainのwarファイルを参照してappのビルドを行うことはしません。
gradleの仕組み上、warファイルが生成されていなくても、依存関係の記述を追記するだけでdomainのリソースが全てappのクラスパスに追加されます。
ちなみに、appプロジェクトに提供されるdomainのリソース内容は「domainプロジェクトのjarファイル相当」に該当します。


# マルチプロジェクトにするための手順
1.rootプロジェクトのsettings.gradleにプロジェクト名を全て記述する
  → include('foo','bar')

2.プロジェクトの上限関係を各プロジェクトの「build.gradle」に記述する
  → 例 implementation project(':core') 

3. 各プロジェクトの「build.gradle」にjarやwarの生成条件を記載する
  →jar {
     enabled = false
     archiveClassifier.set('')
   } 

    war {
      enabled = false
      archiveClassifier.set('')
    }

4.Mainクラスを実行するプロジェクトのMainクラスにアノテーションを付与する
  → この記述が無いと、メインではないプロジェクトのソースのインスタンスなどがnullになって起動しない
  → @ComponentScan(basePackages = {"com.example.app", "com.example.core"})
    

# warファイルやjarファイルの生成に使用する gradle コマンド

(rootにて)
./gradlew clean build

root配下の「setitngs.gradle」に記述した 「include」の内容に応じたプロジェクトがビルドされます
各プロジェクトの「build.gradle」ファイルの内容に応じたwarファイルやjarファイルが生成されます。
AWSのVPC上でアプリケーションを展開する場合は、これらのwarファイルを使用することになります。

# アプリの起動に関して
appプロジェクトのMainクラスを実行することでプロジェクトが起動します。
