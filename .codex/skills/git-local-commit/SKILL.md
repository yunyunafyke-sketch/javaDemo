---
name: git-local-commit
description: 根据当前对话上下文和 Git 实际改动，识别最近未提交内容并静默完成一次本地 commit（不 push）。当用户要求“提交当前改动”“帮我本地 commit”“commit”“把本轮修改提交一下”或类似诉求时使用。若本对话中已经提交过，则从当前工作区断点继续，只提交新的未提交内容。

---

# Git Local Commit 本地 Git 提交

## 目标

根据当前对话上下文和 Git 工作区实际改动，自动识别本次应提交的内容，并完成一次本地 `git commit`。

只做本地提交，不执行 `git push`。

## 使用场景

当用户表达以下或类似意图时使用本 Skill：

- 提交当前改动
- 帮我本地 commit
- commit
- 把本轮修改提交一下
- 把这些改动提交了
- 提交一下代码

## 执行规则

1. 查看当前 Git 状态。
2. 识别未提交文件。
3. 查看本次实际改动内容。
4. 结合当前对话上下文、文件名、diff 内容和最近一次 commit，判断本次改动的真实业务含义。
5. 生成符合规范的中文 commit message。
6. 执行本地 commit。
7. 不执行 push。

如果本对话中已经提交过，则只提交当前工作区中新的未提交内容。

## Git 操作要求

必须执行：

```bash
git status
git diff
git diff --cached
git log -1 --oneline
```

根据实际情况执行：

```bash
git add .
git commit -m "<commit message>"
```

禁止执行：

```bash
git push
```

## Commit Message 格式

统一格式：

```text
<类型>: <中文描述>
```

## 类型规范

仅允许使用以下类型前缀：

feat
fix
refactor
docs
test
chore
perf
style

## 中文规范

类型前缀允许使用英文。

冒号后的描述必须使用简体中文。