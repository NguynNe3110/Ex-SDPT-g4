# Tài liệu Hướng dẫn & Tìm hiểu Git chi tiết

---

## PHẦN 1: LÝ THUYẾT & KIẾN TRÚC GIT

### 1. Kiến trúc về Git và các thành phần hoạt động
Git hoạt động dựa trên cơ chế phân tán (**Distributed Version Control System**). Một repository Git cục bộ và từ xa gồm 4 vùng hoạt động chính:

#### a) Các vùng hoạt động (Areas/States)
* **Working Directory (Thư mục làm việc):** Nơi chứa các file mã nguồn vật lý hiển thị trên máy tính của bạn. Tại đây, các file ở trạng thái chưa theo dõi (`untracked`) hoặc đã chỉnh sửa (`modified`).
* **Staging Area / Index (Vùng đệm):** Một vùng chuẩn bị (thực chất là một file index trong `.git`). Bạn thêm các thay đổi muốn đóng gói vào đây trước khi commit. Trạng thái file là `staged`.
* **Local Repository (Kho cục bộ):** Lưu trữ toàn bộ lịch sử commit, các nhánh (branches) dưới dạng cơ sở dữ liệu đồ thị cục bộ (trong thư mục ẩn `.git`). Trạng thái file là `committed`.
* **Remote Repository (Kho từ xa):** Kho lưu trữ được lưu trữ trên cloud (như GitHub, GitLab). Giúp chia sẻ lịch sử và code để làm việc nhóm.

#### b) Sơ đồ hoạt động và luồng di chuyển của các câu lệnh

```
          [ Working Directory ]
                    │
                    │ git add <file>
                    ▼
           [ Staging Area ] (Index)
                    │
                    │ git commit -m "message"
                    ▼
         [ Local Repository ] (.git)
           ▲              │
           │              │ git push origin <branch>
   git     │              ▼
   pull  / │            [ Remote Repository ] (GitHub/GitLab)
   clone   │              │
           │              │ git fetch
           └──────────────┘
```

#### c) Tác động của các câu lệnh chính
* `git init`: Khởi tạo kho lưu trữ Git cục bộ mới.
* `git status`: Kiểm tra trạng thái hiện tại của các file (modified, staged, untracked).
* `git add`: Di chuyển file từ *Working Directory* sang *Staging Area*.
* `git commit`: Lưu trữ vĩnh viễn ảnh chụp trạng thái từ *Staging Area* vào lịch sử *Local Repository*.
* `git push`: Tải toàn bộ các commit cục bộ lên *Remote Repository*.
* `git fetch`: Tải các commit mới từ remote về *Local Repo* nhưng chưa gộp vào code hiện tại.
* `git merge`: Hợp nhất các commit từ nhánh khác hoặc remote vào nhánh hiện tại.
* `git pull`: Tải code mới và tự động gộp thẳng vào nhánh hiện tại (`fetch` + `merge`).
* `git checkout` / `git switch`: Chuyển đổi giữa các nhánh hoặc khôi phục file.

---

### 2. Tại sao đẩy lên server cần 3 lệnh nhưng lấy về chỉ cần 1?

Sự khác biệt này đến từ triết lý thiết kế **phân tán** và khả năng làm việc **offline** của Git:

* **Khi đẩy lên (Push) cần 3 câu lệnh:**
  1. `git add`: Cho phép chọn lọc chính xác những thay đổi nào được chuẩn bị ghi nhận (stage). Bạn có thể sửa nhiều file nhưng chỉ muốn đưa một số file vào phiên bản tiếp theo.
  2. `git commit`: Đóng gói và lưu phiên bản đó cục bộ. Việc này giúp bạn tạo ra lịch sử rõ ràng với các commit nhỏ mà không cần kết nối mạng.
  3. `git push`: Đẩy toàn bộ các gói commit đó lên máy chủ chung.
  > Tách biệt 3 bước giúp tăng tính linh hoạt và khả năng kiểm soát chất lượng code trước khi chia sẻ với nhóm.

* **Khi lấy về (Pull) chỉ cần 1 câu lệnh:**
  * `git pull` thực chất là lệnh gộp của `git fetch` (tải code mới nhất từ remote về local) và `git merge` (gộp code đó vào nhánh làm việc hiện tại). Vì mục đích phổ biến khi lấy code về là đồng bộ hóa để tiếp tục làm việc, Git hỗ trợ gộp hai hành động này vào một lệnh duy nhất để tiết kiệm thời gian.

---

### 3. Ý nghĩa của các khái niệm và câu lệnh chính

* **Config (`git config`):** Thiết lập thông tin cấu hình cho Git.
  * `--global`: Áp dụng cấu hình cho tất cả repo của người dùng trên máy tính đó.
  * `--list`: Hiển thị toàn bộ cấu hình hiện tại.
* **Khởi tạo repo (`git init`):** Khởi tạo một kho lưu trữ Git mới trong thư mục hiện tại.
* **Clone (`git clone <url>`):** Sao chép toàn bộ mã nguồn và lịch sử của một dự án từ remote về máy tính cục bộ.
* **Pull / Push:**
  * `git pull`: Kéo code mới về và tự động gộp.
  * `git push`: Đẩy code mới từ local lên remote.
* **Tạo và sử dụng nhánh (Branching):** Nhánh giúp phát triển các tính năng độc lập mà không ảnh hưởng tới luồng code chính (`main`).
  * `git branch <branch_name>`: Tạo nhánh mới.
  * `git checkout <branch_name>` / `git switch <branch_name>`: Chuyển nhánh.
* **Merge (`git merge <branch_name>`):** Gộp lịch sử thay đổi của nhánh chỉ định vào nhánh hiện hành.
* **Log (`git log`):** Xem lại lịch sử các commit đã thực hiện trong kho.
* **Conflict (Xung đột):** Xảy ra khi hai nhánh cùng sửa đổi ở cùng một dòng trong một file và Git không thể tự gộp được, yêu cầu lập trình viên can thiệp thủ công.
* **Revert (`git revert <commit_hash>`):** Tạo ra một commit mới để đảo ngược lại các thay đổi của commit cũ được chỉ định (đảm bảo an toàn lịch sử).
* **Ignore (`.gitignore`):** File khai báo các thư mục và file không muốn Git theo dõi hoặc đẩy lên máy chủ.

---

### 4. So sánh các khái niệm dễ nhầm lẫn trong Git

#### a) Khác biệt giữa `git pull` và `git merge`
* **`git merge`:** Là hành động **hợp nhất** lịch sử của hai nhánh khác nhau ở máy cục bộ (local). Lệnh này không liên quan trực tiếp đến mạng internet hay remote repository. Ví dụ: merge nhánh `feature-A` vào nhánh `main`.
* **`git pull`:** Là hành động **tải xuống và hợp nhất** từ máy chủ từ xa (remote). Nó là sự kết hợp của hai bước:
  1. `git fetch`: Tải các thay đổi mới nhất từ remote về nhưng chưa gộp vào code hiện tại.
  2. `git merge`: Tự động gộp các thay đổi vừa tải về vào nhánh làm việc hiện tại của bạn.
* **Tóm lại:** `git pull` = `git fetch` + `git merge`. Bạn chỉ dùng `pull` khi cần đồng bộ code từ internet, còn `merge` có thể dùng offline giữa các nhánh cục bộ.

#### b) Khác biệt giữa `git revert` và `git reset`
Đây là hai lệnh dùng để **hoàn tác** (undo) thay đổi trong Git, nhưng chúng hoạt động theo cơ chế rất khác nhau:

| Tiêu chí | `git revert` | `git reset` |
| :--- | :--- | :--- |
| **Cơ chế hoạt động** | Tạo ra một **commit mới** có nội dung đảo ngược (triệt tiêu) thay đổi của commit cũ. | **Di chuyển con trỏ** `HEAD` quay ngược lại commit trong quá khứ, loại bỏ các commit sau đó khỏi lịch sử của nhánh hiện tại. |
| **Ảnh hưởng lịch sử** | Giữ nguyên lịch sử commit cũ (an toàn khi làm việc nhóm). | Thay đổi/xóa bỏ lịch sử commit cũ (có thể gây rắc rối cho người khác nếu các commit đó đã push lên remote). |
| **Phù hợp cho** | Commit đã được push lên **Remote Repository** chung. | Commit mới làm ở **Local Repository** (chưa push lên remote). |

#### c) Tìm hiểu sâu về các chế độ của `git reset` (`--soft`, `--mixed`, `--hard`)
Lệnh `git reset <commit_hash>` có 3 chế độ (mode) chính ảnh hưởng tới các vùng hoạt động của Git:

1. **`git reset --soft <commit_hash>` (Chế độ nhẹ):**
   * **Cách hoạt động:** Di chuyển con trỏ `HEAD` về commit chỉ định. 
   * **Vùng bị ảnh hưởng:** Giữ nguyên tất cả thay đổi của các commit bị reset trong **Staging Area** (sẵn sàng để commit lại). **Working Directory** không bị thay đổi.
   * **Ứng dụng:** Khi bạn muốn gộp nhiều commit nhỏ vừa tạo thành một commit lớn duy nhất trước khi push (Squash commit).

2. **`git reset --mixed <commit_hash>` (Chế độ mặc định):**
   * **Cách hoạt động:** Nếu bạn không truyền tham số, Git mặc định sử dụng `--mixed`.
   * **Vùng bị ảnh hưởng:** Đưa các thay đổi của các commit bị reset trở lại **Working Directory** dưới dạng chưa được add (`unstaged/modified`). **Staging Area** bị làm sạch.
   * **Ứng dụng:** Khi bạn muốn viết lại nội dung commit hoặc chỉnh sửa thêm một số file trước khi add và commit lại.

3. **`git reset --hard <commit_hash>` (Chế độ xóa sạch):**
   * **Cách hoạt động:** Đây là chế độ **nguy hiểm nhất**.
   * **Vùng bị ảnh hưởng:** Xóa sạch toàn bộ thay đổi ở cả **Staging Area** and **Working Directory**. Mọi file sẽ quay trở về trạng thái chính xác của commit được chỉ định. Toàn bộ code chưa commit hoặc các commit sau đó sẽ **bị xóa vĩnh viễn**.
   * **Ứng dụng:** Khi bạn thực sự muốn hủy bỏ hoàn toàn những gì đã làm và bắt đầu lại từ một mốc commit cũ trong quá khứ.

---

### 5. Hướng dẫn giải quyết xung đột (conflict) chi tiết từng bước

Khi thực hiện merge hoặc pull, nếu hai nhánh cùng chỉnh sửa một dòng trong cùng một file, Git sẽ báo lỗi xung đột (conflict). Dưới đây là các bước để xử lý:

#### Bước 1: Tìm các file bị xung đột
Chạy lệnh sau để xem danh sách file đang bị xung đột (nằm trong mục **Both modified**):
```bash
git status
```

#### Bước 2: Xem các ký hiệu xung đột trong file
Mở file bị xung đột bằng trình chỉnh sửa code. Bạn sẽ thấy Git chèn các ký hiệu phân tách xung đột dạng:
```text
<<<<<<< HEAD
[Code của bạn trên nhánh hiện tại (HEAD)]
=======
[Code của nhánh khác đang merge vào]
>>>>>>> [tên_nhánh_được_merge]
```

#### Bước 3: Giải quyết xung đột bằng tay
* Đọc kỹ cả hai đoạn code để quyết định giữ lại code của bạn, lấy code của nhánh kia, hay kết hợp cả hai để tạo ra đoạn code mới hoàn hảo nhất.
* **Lưu ý quan trọng:** Bạn **bắt buộc** phải xóa bỏ hoàn toàn các dòng ký hiệu phân cách tự động của Git (`<<<<<<< HEAD`, `=======`, `>>>>>>> [tên_nhánh]`).

#### Bước 4: Đánh dấu đã giải quyết và hoàn tất Merge
* Lưu file sau khi đã chỉnh sửa sạch sẽ.
* Chạy lệnh add để xác nhận file đã giải quyết xung đột:
  ```bash
  git add <tên_file>
  ```
* Thực hiện commit để hoàn tất quá trình merge:
  ```bash
  git commit -m "Resolve merge conflict between <nhánh_A> and <nhánh_B>"
  ```
* Cuối cùng, thực hiện push code sạch lên remote:
  ```bash
  git push origin <tên_nhánh>
  ```

---

## PHẦN 2: THỰC HÀNH CÁC TÌNH HUỐNG GIT

### Câu 1: Tạo repo như thế nào? Public và private khác nhau ra sao? Mời bạn bè ra sao?
* **Cách tạo repo:**
  * *Local:* Vào thư mục dự án và chạy `git init`.
  * *Remote (GitHub):* Đăng nhập -> nhấn biểu tượng **New** (hoặc nút **+**) -> Nhập tên repo -> Click **Create repository**.
* **Phân biệt Public và Private:**
  * **Public Repo:** Tất cả mọi người trên internet đều có thể xem code, clone dự án. Tuy nhiên, chỉ những người được cấp quyền cộng tác viên mới có thể đẩy code trực tiếp lên.
  * **Private Repo:** Chỉ chủ sở hữu và những người được mời đích danh mới có quyền truy cập, xem code, clone và đẩy code.
* **Cách mời bạn bè (GitHub):**
  * Vào Repo của bạn -> Chọn tab **Settings** -> Chọn mục **Collaborators** -> Chọn **Add people** -> Nhập email hoặc username GitHub của bạn bè để gửi lời mời.

### Câu 2: Gitconfig có tác dụng gì? Sử dụng khi nào? Có thể thay đổi không?
* **Tác dụng:** Lưu trữ thông tin cá nhân của người dùng (`user.name`, `user.email`), trình chỉnh sửa văn bản mặc định, các phím tắt lệnh (alias), cấu hình kết thúc dòng (line endings), v.v.
* **Sử dụng khi:** Bắt đầu cài đặt Git trên máy tính, khi cần chuyển đổi giữa email công việc và cá nhân cho từng dự án cụ thể.
* **Thay đổi thông tin:** Hoàn toàn có thể sửa đổi bằng lệnh hoặc sửa trực tiếp file cấu hình:
  * Lệnh thay đổi global:
    ```bash
    git config --global user.name "Tên của bạn"
    git config --global user.email "your.email@example.com"
    ```
  * Hoặc mở file `.gitconfig` tại thư mục người dùng (`C:\Users\<Username>\.gitconfig` trên Windows) để chỉnh sửa thủ công.

### Câu 3: Bạn A tạo 01 repo, đẩy 1 source nhỏ lên git và tạo nhánh main
Các bước thực hiện của bạn A:
```bash
# Khởi tạo repo cục bộ
git init

# Đưa toàn bộ file vào staging và tạo commit đầu tiên
git add .
git commit -m "Initial commit"

# Tạo/đổi tên nhánh chính thành main
git branch -M main

# Liên kết với Remote Repository trên GitHub
git remote add origin https://github.com/UserA/example-repo.git

# Đẩy code lên nhánh main
git push -u origin main
```

### Câu 4: Bạn B, C, D tạo các nhánh riêng biệt, lấy code nhánh main về và chỉnh sửa
Các bước thực hiện của B, C, D:
```bash
# Clone dự án về máy cục bộ
git clone https://github.com/UserA/example-repo.git
cd example-repo

# Tạo và chuyển sang nhánh làm việc riêng
# Bạn B:
git checkout -b feature-B
# Bạn C:
git checkout -b feature-C
# Bạn D:
git checkout -b feature-D

# Thực hiện sửa đổi mã nguồn trên nhánh của mình...
```

### Câu 5: Thực hiện đẩy code chỉnh sửa lên nhánh của mình như thế nào?
Để đẩy thay đổi trên nhánh hiện tại lên Remote:
```bash
# 1. Thêm các thay đổi vào Staging Area
git add .

# 2. Tạo commit cục bộ
git commit -m "Mô tả các thay đổi đã thực hiện"

# 3. Đẩy lên nhánh của mình trên remote (ví dụ nhánh feature-B)
git push origin feature-B
```

### Câu 6: Chuyển nhánh như thế nào?
* Cách hiện đại và khuyến nghị:
  ```bash
  git switch <tên_nhánh>
  ```
* Cách truyền thống:
  ```bash
  git checkout <tên_nhánh>
  ```
* *Lưu ý:* Hãy commit hoặc cất các thay đổi dở dang (`git stash`) trước khi chuyển nhánh để tránh xung đột hoặc mất mát dữ liệu.

### Câu 7: Kiểm tra trạng thái thay đổi của việc làm việc như thế nào?
* Kiểm tra tổng quan:
  ```bash
  git status
  ```
* Xem chi tiết sự thay đổi của từng dòng code (khi chưa add vào staging):
  ```bash
  git diff
  ```
* Xem chi tiết sự thay đổi của các file đã add vào staging:
  ```bash
  git diff --staged
  ```

### Câu 8: Bạn B tạo nhánh mới. Làm thế nào để C, D lấy được thông tin nhánh đó?
1. Bạn B cần đẩy nhánh cục bộ lên Remote trước:
   ```bash
   git push origin feature-B
   ```
2. Bạn C và D cập nhật danh sách các nhánh mới từ Remote về máy mình:
   ```bash
   git fetch origin
   ```
3. Sau đó C và D chuyển sang nhánh của B để xem/làm việc:
   ```bash
   git checkout feature-B
   ```

### Câu 9: Bạn C muốn hợp nhất (merge) với nhánh B thì làm như thế nào?
1. Bạn C lấy thông tin mới nhất của nhánh B từ remote:
   ```bash
   git fetch origin
   ```
2. Chuyển về nhánh của mình:
   ```bash
   git checkout feature-C
   ```
3. Thực hiện merge nhánh B vào:
   * Nếu merge từ nhánh cục bộ đã tải về:
     ```bash
     git merge feature-B
     ```
   * Nếu merge trực tiếp từ nhánh trên remote:
     ```bash
     git merge origin/feature-B
     ```

### Câu 10: Tạo thư mục config nhưng không muốn đẩy lên git (Git Ignore)
1. Tại thư mục gốc của repo, mở hoặc tạo file `.gitignore`.
2. Thêm dòng khai báo thư mục `config/` vào file:
   ```text
   config/
   ```
3. Lưu file lại. Từ giờ, thư mục `config` sẽ bị Git bỏ qua.
*Nếu thư mục `config` đã lỡ được push trước đó, chạy lệnh sau để xóa khỏi cache của Git:*
```bash
git rm -r --cached config
```

### Câu 11: Muốn xem lại những ai đã commit thì làm như thế nào?
* Xem lịch sử commit chi tiết:
  ```bash
  git log
  ```
* Xem lịch sử rút gọn trên 1 dòng:
  ```bash
  git log --oneline
  ```
* Thống kê danh sách các tác giả đã commit kèm số lượng commit tương ứng:
  ```bash
  git shortlog -sn
  ```

### Câu 12: Muốn xem cụ thể thông tin đã chỉnh sửa trong một commit thì làm như thế nào?
Sử dụng mã băm (hash) của commit đó thu được từ `git log`:
```bash
git show <commit_hash>
# Ví dụ: git show a1b2c3d
```

### Câu 13: Gắn thẻ (Tagging) thì thực hiện như thế nào?
* Tạo thẻ gọn nhẹ (Lightweight tag):
  ```bash
  git tag v1.0.0
  ```
* Tạo thẻ có thông điệp chú thích (Annotated tag - khuyến nghị cho release):
  ```bash
  git tag -a v1.0.0 -m "Bản phát hành chính thức phiên bản 1.0.0"
  ```
* Đẩy tag lên Remote (lệnh push thường không tự đẩy tag):
  * Đẩy 1 tag cụ thể: `git push origin v1.0.0`
  * Đẩy tất cả tag cục bộ lên: `git push origin --tags`

### Câu 14: Revert thì thực hiện như thế nào?
Để đảo ngược một commit đã tồn tại mà không thay đổi cấu trúc lịch sử trước đó:
```bash
# 1. Tìm commit hash muốn huỷ bỏ
git log --oneline

# 2. Thực hiện revert commit đó
git revert <commit_hash>

# 3. Đẩy thay đổi an toàn này lên Remote
git push origin <branch_name>
```

### Câu 15: Tạo trường hợp bạn B và C merge bị conflict và xử lý nó

#### Tình huống xảy ra xung đột:
Nhánh chung `main` có file `document.txt` chứa nội dung ban đầu:
```text
Dòng 5: Nội dung gốc của hệ thống.
```
* **Bạn B:** Trên nhánh `feature-B`, sửa dòng 5 thành: `Dòng 5: Nội dung đã được Bạn B cải tiến.` rồi commit và đẩy lên merge thành công vào nhánh `main`.
* **Bạn C:** Trên nhánh `feature-C` (chưa fetch code mới), sửa dòng 5 thành: `Dòng 5: Nội dung đã được chỉnh sửa bởi Bạn C.` rồi commit cục bộ.

#### Khi merge phát sinh xung đột:
Khi bạn C tiến hành cập nhật nhánh `main` mới nhất về và gộp vào nhánh của mình:
```bash
git merge main
```
Hệ thống sẽ báo lỗi:
```text
CONFLICT (content): Merge conflict in document.txt
Automatic merge failed; fix conflicts and then commit the result.
```

#### Cách xử lý xung đột:
1. Bạn C mở file `document.txt` lên, thấy các ký hiệu phân cách xung đột:
   ```text
   <<<<<<< HEAD
   Dòng 5: Nội dung đã được chỉnh sửa bởi Bạn C.
   =======
   Dòng 5: Nội dung đã được Bạn B cải tiến.
   >>>>>>> main
   ```
2. Bạn C thảo luận với bạn B để đưa ra phương án chỉnh sửa chung cuối cùng.
3. Tiến hành xóa bỏ các ký hiệu phân tách (`<<<<<<<`, `=======`, `>>>>>>>`) và chỉnh sửa dòng code xung đột. Ví dụ giữ cả hai và gộp lại:
   ```text
   Dòng 5: Nội dung đã được chỉnh sửa bởi Bạn C và Bạn B cùng cải tiến.
   ```
4. Lưu file lại.
5. Thực hiện add file đã giải quyết xung đột vào staging:
   ```bash
   git add document.txt
   ```
6. Commit để hoàn thành merge:
   ```bash
   git commit -m "Resolve merge conflict between feature-C and main"
   ```
7. Đẩy code đã sạch xung đột lên remote:
   ```bash
   git push origin feature-C
   ```
