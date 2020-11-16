\newif\if@gmcm@bwprint\@gmcm@bwprintfalse
\newif\if@gmcm@preface\@gmcm@prefacetrue

\newcommand\gmcm@tokens@keywords{}
\newcommand*\gmcm@tokens@tihao{}
\newcommand*\gmcm@tokens@baominghao{}
\newcommand*\gmcm@tokens@schoolname{}
\newcommand*\gmcm@tokens@membera{}
\newcommand*\gmcm@tokens@memberb{}
\newcommand*\gmcm@tokens@memberc{}
\newcommand*\gmcm@tokens@supervisor{}
\newcommand*\gmcm@tokens@yearinput{}
\newcommand*\gmcm@tokens@monthinput{}
\newcommand*\gmcm@tokens@dayinput{}


\DeclareOption{colorprint}{\@gmcm@bwprintfalse}
\DeclareOption{bwprint}{\@gmcm@bwprinttrue}
\DeclareOption{withoutpreface}{\@gmcm@prefacefalse}
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{ctexart}}

\ExecuteOptions{colorprint}
\ProcessOptions\relax

\LoadClass[a4paper,UTF8]{ctexart}

\RequirePackage{ifxetex}
\RequireXeTeX
\ifxetex\else
\ClassError{mcmthesis}{You must use the `xelatex' driver\MessageBreak Please choose `xelatex'}{%
	Just choose `xelatex', no `pdflatex' or `latex' and so on.}
\fi



%---------------------导入必要的宏包----------------------------------------
% 数学宏包
\RequirePackage{amsmath,amssymb,theorem,caption,extarrows}
\RequirePackage{bm}

% 设置颜色
\RequirePackage{xcolor}
% 插入图片
\RequirePackage{graphicx}
% 表格
\RequirePackage{tabularx,array}
%% 长表格
\RequirePackage{longtable}
%% booktabs 提供了\toprule 等命令.
\RequirePackage{booktabs}
%% multirow 支持在表格中跨行
\RequirePackage{multirow}
%% 调整间隔, 让表格更好看些
\RequirePackage{bigstrut}
%% 在跨行表格中输入定界符
\RequirePackage{bigdelim}
% 保护脆落命令
\RequirePackage{cprotect}


% 首行缩进
\RequirePackage{indentfirst}
% 设置浮动体的标题
\RequirePackage{caption}
% 定制列表环境
\RequirePackage{enumitem}
% 下划线
\RequirePackage{ulem}
% 尺寸计算
\RequirePackage{calc}
\RequirePackage{titletoc,url}
%参考文献
\RequirePackage[numbers]{natbib}
\setlength{\bibsep}{0pt plus 0.3ex}

\RequirePackage{etoolbox}
\AtBeginEnvironment{thebibliography}{%
	\phantomsection
	\addcontentsline{toc}{section}{\refname}
}
% 超链接 hyperref 的设置
\RequirePackage{hyperref}

%---------------------导入必要的宏包----------------------------------------




%---------------设置latex中插入的程序代码格式----------------------------------------

%导入listings宏包
\RequirePackage{listings}

\lstset{
    basicstyle          =   \sffamily,          % 基本代码风格
    keywordstyle        =   \bfseries,          % 关键字风格
    commentstyle        =   \rmfamily\itshape,  % 注释的风格，斜体
    stringstyle         =   \ttfamily,  % 字符串风格
    flexiblecolumns,                % 别问为什么，加上这个
    numbers             =   left,   % 行号的位置在左边
    showspaces          =   false,  % 是否显示空格，显示了有点乱，所以不现实了
    numberstyle         =   \zihao{-5}\ttfamily,    % 行号的样式，小五号，tt等宽字体
    showstringspaces    =   false,
    captionpos          =   t,      % 这段代码的名字所呈现的位置，t指的是top上面
    frame               =   tb,   % 显示边框
}

% 设置Python代码的格式
\lstdefinestyle{Python}{
    language        =   Python, % 语言选Python
    basicstyle      =   \zihao{-5}\ttfamily,
    numberstyle     =   \zihao{-5}\ttfamily,
    keywordstyle    =   \color{blue},
    keywordstyle    =   [2] \color{teal},
    stringstyle     =   \color{magenta},
    commentstyle    =   \color[HTML]{338AAF}\ttfamily,
    breaklines      =   true,   % 自动换行，建议不要写太长的行
    columns         =   fixed,  % 如果不加这一句，字间距就不固定，很丑，必须加
    basewidth       =   0.5em,
}
\definecolor{codegreen}{rgb}{0,0.6,0}
\definecolor{codegray}{rgb}{0.5,0.5,0.5}
\definecolor{codepurple}{rgb}{0.58,0,0.82}
\definecolor{backcolour}{rgb}{0.95,0.95,0.92}

%设置Mathematica代码的格式
\lstdefinestyle{mathematica}{
    backgroundcolor=\color{backcolour},
    commentstyle=\color[HTML]{338AAF}\ttfamily,
    keywordstyle=\zihao{-5}\sffamily\bfseries\color{magenta},
    numberstyle=\tiny\color{codegray},
    stringstyle=\color{codepurple},
    basicstyle=\zihao{-5}\ttfamily,
    breakatwhitespace=false,
    breaklines=true,
    basewidth=0.5em,
    captionpos=b,
    columns=fixed,
    keepspaces=true,
    numbers=left,
    numbersep=5pt,
    showspaces=false,
    showstringspaces=false,
    showtabs=false,
    tabsize=2
}

%---------------设置latex中插入的程序代码格式---------------------------------------



%-----------------------设置页面布局-------------------------------------
% 导入页面布局的宏包
\RequirePackage{geometry}
\geometry{left=1.8cm,right=1.8cm,top=1.5cm,bottom=1.5cm,headsep=8mm}
% 利用宏包 geometry设置上、下、左、右的页边距以及标题和正文之间的间距

\renewcommand*{\baselinestretch}{1.38}
% 将行间距调整为1.38倍行距

% 设置标题页
\renewcommand\title[1]{\def\IHEP@value@title{#1}}
\renewcommand\author[1]{\def\IHEP@value@author{#1}}
\renewcommand\date[1]{\def\IHEP@value@date{#1}}

\renewcommand\maketitle{%
      \clearpage
      \thispagestyle{empty}
      \vspace*{50pt}
      \begin{center}
      {\bf\zihao{-1}南方科技大学物理系\\讨论班讲义}
      \vskip 50pt
      {\bf\songti\zihao{3} \underline{~\IHEP@value@title~}}
      \vskip 20pt
      {\zihao{4} \underline{~\IHEP@value@author~}}
      \vskip 10pt
      \zihao{5} \underline{~\IHEP@value@date~}
      \end{center}
      \pagebreak
}

\def\IHEP@value@title{~~~~~（笔~记~题~目）~~~~~}
\def\IHEP@value@author{~~~~~（作~者~姓~名）~~~~~}
\def\IHEP@value@date{~~~~~(编~辑~日~期)~~~~~}

%-----------------------设置页面布局-------------------------------------



%----------------------设置首行缩进------------------------------
% 导入首行缩进用的宏包
\RequirePackage{indentfirst}
% 每行缩进两个汉字
\setlength{\parindent}{2em}
%----------------------设置首行缩进------------------------------



%----------------------设置文章字体------------------------------
% 设置英文字体

% 设置中文字体
\RequirePackage{xeCJK,fontspec}
\setCJKmainfont[BoldFont=方正小标宋简体,ItalicFont=楷体]{华文宋体}
\setCJKfamilyfont{song}{华文宋体}
  \setCJKsansfont{黑体}%serif是有衬线字体sans serif无衬线字体。
  %\setmonofont{CMU Typewriter Text} % 等寬字型
  \XeTeXlinebreaklocale "zh"
  \XeTeXlinebreakskip = 0pt plus 1pt minus 0.1pt
\ifx\lishu\undefined%
\setCJKfamilyfont{zhli}{LiSu}
\newcommand*{\lishu}{\CJKfamily{zhli}}
\else
\fi
%----------------------设置文章字体------------------------------



%------------------------浮动环境设置-----------------------------------------------------------

% 下面给出的命令用来控制一页中有多大比例的区域可用来放置浮动对象
% (这里的比例是指浮动对象的高度除以正文高度\textheight)。
% 前面三个命令只作用于文本页，而最后一个命令只作用于浮动页。这些命令的值可以用 \renewcommand 来修改。
% (1) \textfraction：页面中必须用来排放文本的最小比例。缺省值为 0.2，即要求每页的文字至少占据 20%。
% 而这通常不是我们想要的, 我们将这个要求降低到 5%。
% (2) \topfraction：页面顶部可以用来放置浮动对象的高度与整个页面高度的最大比例。缺省值为 0.7，即
% 放置在页顶部的浮动对象所占的高度不得超过整个页面高度 70%。
% (3) \bottomfraction: 页面底部可以用来放置浮动对象的高度与整个页面高度的最大比例。缺省值为 0.3。
% 有时如果多个浮动环境连续放在一起, latex也会将它们分在几个不同页，即使它们可在同一页放得下。
% 我们可以通过修改 \topfraction 和 \bottomfraction 分别设置顶端和底端的浮动环境的最大比例。
% (4) \floatpagefraction: 浮动页中必须由浮动对象占用的最小比例。因此在一浮动页中空白所占的比例
% 不会超过 1 - \floatpagefraction。缺省值为 0.5。
% 有时 LaTeX 会把一个浮动环境单独放在一页，我们通过设置要求这个环境至少要占据 85% 才能单独放在一页。

\renewcommand*{\textfraction}{0.05}
\renewcommand*{\topfraction}{0.9}
\renewcommand*{\bottomfraction}{0.8}
\renewcommand*{\floatpagefraction}{0.85}

%------------------------浮动环境设置-----------------------------------------------------------


%------------------插图、表格以及列表环境设置---------------------------------------------------

% 关于图片宏包graphicx，如果图片没有指定后缀, 依次按下列顺序搜索
\DeclareGraphicsExtensions{.pdf,.eps,.jpg,.png}

% 设置图表搜索路径, 可以给图表文件夹取如下名字
\graphicspath{{figures/}{figure/}{pictures/}{picture/}{pic/}{pics/}{image/}{images/}}

% 声明标题的字体、字号
\DeclareCaptionFont{hei}{\heiti}
\DeclareCaptionFont{minusfour}{\zihao{-4}}

% 如果文章中有section，那么插图标题标签将是1.1, 1.2, 2.1等。
% 我们可以如下设置，该命令指定了一个将更改的标签（如： \thefigure ）以及希望显示的标签
% 类型(如：\arabic{figure} ), 也就是说要将插图的序列号显示为阿拉伯数字，如1, 2, 3等。
\renewcommand{\thefigure}{\thesection.\arabic{figure}}

% 可以使用 \captionsetup 设置标题样式，这样后面所有的标题样式都是根据 \captionsetup 重新设置的，
% 即 \captionsetup[FLOAT_TYPE]{OPTIONS}
% 其中可选参数FLOAT_TYPE 可以是table、subtable、figure、subfigure等。
\captionsetup[figure]{
	format=hang,                     % 标题从第二行开始都有缩进, 应该和 justification=raggedright 的效果一样.
	labelsep=quad,                   % 分隔符是一个空格，指标题名称和序号直接的空隙
	font={minusfour,bf},        % 将fugure环境中的字体设置为： 宋体小四
	position=bottom                  % position=bottom, 不代表标题放在下面, 标题仍放在你放\caption的位置
}


% 表格环境设置
\captionsetup[table]{%
	format=hang,   % 标题从第二行开始都有缩进, 应该和 justification=raggedright 的效果一样.
	labelsep=quad, % 分隔符是一个空格
	font={minusfour,bf}, % 表的字体, 宋体小四
	position=top % position=bottom, 不代表标题放在下面, 标题仍放在你放\caption的位置.
}


% 列表环境设置
% 列表就是将所要表达的内容分为若干个条目并按一定的顺序排列，达到简明、直观的效果。在论文的写作中会经常使用到列表。
% LaTeX 中常见的列表环境有 enumerate、itemize 和 description。这三种列表环境的主要区别是列表项标签的不同。
% enumerate 是有序的列表； itemize 以圆点作为标签；description 是解说列表，可以指定标签。
\setlist{%
	topsep=0.3em, % 列表顶端的垂直空白
	partopsep=0pt, % 列表环境前面紧接着一个空白行时其顶端的额外垂直空白
	itemsep=0ex plus 0.1ex, % 列表项之间的额外垂直空白
	parsep=0pt, % 列表项内的段落之间的垂直空白
	leftmargin=1.5em, % 环境的左边界和列表之间的水平距离
	rightmargin=0em, % 环境的右边界和列表之间的水平距离
	labelsep=0.5em, % 包含标签的盒子与列表项的第一行文本之间的间隔
	labelwidth=2em % 包含标签的盒子的正常宽度；若实际宽度更宽，则使用实际宽度。
}

%------------------------插图、表格以及列表环境设置------------------------------------------




%------------------------算法（伪代码）的环境设置----------------------------------------------
\usepackage{algorithm}
\usepackage{algpseudocode}
\usepackage{amsmath}
\floatname{algorithm}{算法}
\renewcommand{\algorithmicrequire}{\textbf{输入:}}   % Use Input in the format of Algorithm
\renewcommand{\algorithmicensure}{\textbf{输出:}}    % Use Output in the format of Algorithm
%------------------------算法（伪代码）的环境设置----------------------------------------


%----------------------------设置目录的超链接--------------------------------------------------------
% \AtBeginDocument{%
\hypersetup{%
	% % unicode=false, % hyperref 和 xetex 同时使用时不能开启 Unicode 选项.
	hyperfootnotes=true,
	hyperindex=true,
	colorlinks=true,
	bookmarksnumbered=true,
	bookmarksopen=true,
	bookmarksopenlevel=0,
	allcolors=black,
	breaklinks=true}%
% }
% \if@gmcm@bwprint
% \AtBeginDocument{\hypersetup{hidelinks}}
% \else\relax\fi
\def\UrlAlphabet{%
	\do\a\do\b\do\c\do\d\do\e\do\f\do\g\do\h\do\i\do\j%
	\do\k\do\l\do\m\do\n\do\o\do\p\do\q\do\r\do\s\do\t%
	\do\u\do\v\do\w\do\x\do\y\do\z\do\A\do\B\do\C\do\D%
	\do\E\do\F\do\G\do\H\do\I\do\J\do\K\do\L\do\M\do\N%
	\do\O\do\P\do\Q\do\R\do\S\do\T\do\U\do\V\do\W\do\X%
	\do\Y\do\Z}
\def\UrlDigits{\do\1\do\2\do\3\do\4\do\5\do\6\do\7\do\8\do\9\do\0}
\g@addto@macro{\UrlBreaks}{\UrlOrds}
\g@addto@macro{\UrlBreaks}{\UrlAlphabet}
\g@addto@macro{\UrlBreaks}{\UrlDigits}

%----------------------------设置目录的超链接--------------------------------------------------------


%--------------------------------中文标题格式设置-----------------------------------------

% 通过 setcounter 命令来控制目录深度,如显示三级目录
\setcounter{secnumdepth}{3}
\def\@seccntformat#1{\csname the#1\endcsname\ \ }

% 更改节、子节等的标题前序号的格式
\renewcommand\thesection{\arabic{section}.}
\renewcommand\thesubsection{\arabic{section}\thinspace.\thinspace\arabic{subsection}}
\renewcommand\thesubsubsection{\thesubsection\thinspace.\thinspace\arabic{subsubsection}}

% 节标题格式, 居中，字体采用 \normalfont，大小采用 \normalsize
\renewcommand\section{\@startsection{section}{1}{\z@}%
	{2.5ex \@plus -1ex \@minus -.2ex}%
	{2.3ex \@plus.2ex}%
	{\bfseries\centering\zihao{4}\heiti}}
\renewcommand\subsection{\@startsection{subsection}{2}{\z@}%
	{1.25ex\@plus -1ex \@minus -.2ex}%
	{1.25ex \@plus .2ex}%
	{\normalfont\normalsize\bfseries}}
\renewcommand\subsubsection{\@startsection{subsubsection}{3}{\z@}%
	{1.25ex\@plus -1ex \@minus -.2ex}%
	{1.2ex \@plus .2ex}%
	{\normalfont\normalsize\bfseries}}
\renewcommand\paragraph{\@startsection{paragraph}{4}{\z@}%
	{3.25ex \@plus1ex \@minus.2ex}%
	{1em}%
	{\normalfont\normalsize\bfseries}}
\renewcommand\subparagraph{\@startsection{subparagraph}{5}{\parindent}%
	{3.25ex \@plus1ex \@minus .2ex}%
	{1em}%
	{\normalfont\normalsize\bfseries}}



%-------------------------------中文标题格式设置--------------------------------------



%----------------------------以中文重命名数学定理相关的常量---------------------------

% 数学环境, 定理等设置
\newtheorem{definition}{\gmcm@cap@definition}
\newtheorem{theorem}{\gmcm@cap@theorem}
\newtheorem{lemma}{\gmcm@cap@lemma}
\newtheorem{corollary}{\gmcm@cap@corollary}
\newtheorem{assumption}{\gmcm@cap@assumption}
\newtheorem{conjecture}{\gmcm@cap@conjecture}
\newtheorem{axiom}{\gmcm@cap@axiom}
\newtheorem{principle}{\gmcm@cap@principle}
\newtheorem{problem}{\gmcm@cap@problem}
\newtheorem{example}{\gmcm@cap@example}
\newtheorem{proof}{\gmcm@cap@proof}
\newtheorem{solution}{\gmcm@cap@solution}

%数学定理相关的常量
\newcommand*{\gmcm@cap@definition}{定义}
\newcommand*{\gmcm@cap@theorem}{定理}
\newcommand*{\gmcm@cap@lemma}{引理}
\newcommand*{\gmcm@cap@corollary}{推论}
\newcommand*{\gmcm@cap@assumption}{假设}
\newcommand*{\gmcm@cap@conjecture}{猜想}
\newcommand*{\gmcm@cap@axiom}{公理}
\newcommand*{\gmcm@cap@principle}{定律}
\newcommand*{\gmcm@cap@problem}{问题}
\newcommand*{\gmcm@cap@example}{例}
\newcommand*{\gmcm@cap@proof}{证明}
\newcommand*{\gmcm@cap@solution}{解}
\def\dif{\mathop{}\!\mathrm{d}}
\def\tr{\mathop{}\!\mathrm{T}}
\def\const{\mathop{}\!\mathrm{const.}}
\def\splitline{\noindent\rule[0.25\baselineskip]{\textwidth}{0.5pt}}

%----------------------------以中文重命名数学定理相关的常量-----------------------------------




%----------------------------设置目录格式-----------------------------------------------

% 节的目录格式
\titlecontents{section}[0pt]{\vspace{2mm}\bfseries\heiti}
{\thecontentslabel\hskip.5em}{}{\titlerule*[0.5pc]{.}\contentspage}
% 小节的目录格式
\titlecontents{subsection}[30pt]{\songti}
{\thecontentslabel\hskip.5em}{}{\titlerule*[0.5pc]{.}\contentspage}

\titlecontents{subsubsection}[55pt]{\songti}
{\thecontentslabel\hskip.5em}{}{\titlerule*[0.5pc]{.}\contentspage}


% itletoc 宏包用于自定义目录样式，其中最常用的是下面这条目录样式命令。
% \titlecontents 命令来设置不同级别目录项的格式：
% \titlecontents{章节名称}[左端距离]{标题字体、与上文间距等}{标题序号}{空}{引导符和页码}[与下文间距]
% (1) 其中0pt（[左端距离]）是目录项到版芯左边界的距离；\vspace{2mm}表示与上文留出一定的垂直距离，该距离为2mm；
% \bfseries\heiti 把整条目录项的字体设为黑体。
% (2) 后面一项是设置目录项的头部，并在其后留出一个汉字宽度的距离。紧跟的是设置目录项主体的格式，
% 这里因为跟目录项头部相同而空置。
% (3) 再后面是设置填充命令和页码。这里用\titlerule*命令画出填充点，
% 这里是把垂直居中的实心圆点作为填充符号(习惯上中文不采用居下的填充点),
% 并以10pt为包含一个填充符号的水平盒子的宽度，即这个宽度越小，填充点越紧密; 填充点后加上页码 \contentspage。

% 注意：用 titlesec 宏包可以在标题中加一些填充物，比如：一条水平线、一排连续或不连续的点等等。用以下三个命令来实现：
% (1) \titleline[<align>]{<horizontalmaterial>}
% 其中中 <align> 表示对齐方式，有三个参数 l、c、r，分别代表左对齐、居中对齐、右对齐；
% <horizontal material> 是要填充的材料，可以是文字、符号等等。
% (2) \titlerule [<height>]：表示在标题中添加一条水平线，<height> 是线的宽度。
% (3) \titlerule ∗[<width >]{<text>}：用于在标题中添加一条填充物，<width> 为填充物的宽度，<text> 为填充的文字或符号。

%----------------------------设置目录格式-----------------------------------------------

\pagestyle{plain}
\endinput
