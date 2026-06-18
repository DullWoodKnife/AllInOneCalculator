# 全能计算器 (All-in-One Calculator)

一个功能丰富的 Android 全能计算器应用，支持多种实用计算功能。

## 功能模块

| 模块 | 功能描述 |
|------|----------|
| **电阻转换** | 支持欧姆、千欧、兆欧、毫欧、微欧之间的相互转换 |
| **油耗量转换** | 支持升/百公里、英里/加仑(美/英)、公里/升等单位转换 |
| **能量转换** | 支持焦耳、千焦、卡路里、千卡、瓦时、千瓦时、电子伏特转换 |
| **年龄计算** | 计算精确年龄(年月日)、总天数/小时/分钟/秒，显示星座和生肖 |
| **BMI指数** | 根据身高体重计算BMI，给出体型分类、健康建议和理想体重范围 |
| **购车计算** | 支持贷款计算(月供、总利息)和燃油费用计算 |
| **退休计算** | 根据当前年龄、收入、储蓄比例计算退休总储蓄和预计月养老金 |
| **亲戚称呼** | 通过关系链推导中国亲戚称呼，如"妈妈的儿子"→"自己/哥哥/弟弟" |

## 技术栈

- **语言**: Java
- **最低 SDK**: API 21 (Android 5.0)
- **目标 SDK**: API 34 (Android 14)
- **依赖库**:
  - AndroidX AppCompat
  - Material Design Components
  - RecyclerView
  - CardView

## 项目结构

```
AllInOneCalculator/
├── app/
│   ├── src/main/java/com/example/calculator/
│   │   ├── activities/     # 界面Activity
│   │   ├── adapters/       # 列表适配器
│   │   ├── models/         # 数据模型
│   │   └── utils/          # 工具类
│   ├── src/main/res/       # 布局和资源文件
│   └── build.gradle        # 模块构建配置
├── build.gradle            # 项目构建配置
└── settings.gradle         # 项目设置
```

## 构建方式

### 使用 Android Studio
1. 打开 Android Studio
2. 选择 `File -> Open`，选择项目文件夹
3. 等待 Gradle 同步完成
4. 点击 `Run` 按钮运行应用

### 使用命令行
```bash
# 进入项目目录
cd AllInOneCalculator

# 构建 Debug 版本
./gradlew assembleDebug

# 构建 Release 版本
./gradlew assembleRelease
```

## GitHub Actions CI/CD

本项目包含 GitHub Actions 工作流，支持：
- 自动构建 APK
- 代码质量检查
- 发布 Release

## 截图

![主界面](screenshots/main_screen.png)
![亲戚称呼](screenshots/relationship_screen.png)

## 许可证

MIT License
