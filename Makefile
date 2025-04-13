#############################################################################
#
# Sources lists
#
#############################################################################

# Project name
NAME		=	avaj_launcher

# Java compiler
JAVAC		=	javac

# Java runtime
JAVA		=	java

# Source directory
SRC_DIR		=	src

# Find all package directories under src
PACKAGE_DIRS	=	$(shell find $(SRC_DIR) -type d)

# Find all .java files in the source directories
SRC			=	$(shell find $(PACKAGE_DIRS) -name "*.java")

# Output directory for compiled classes
BIN_DIR		=	bin

# Create a list of .class files corresponding to the .java files
OBJ			=	$(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRC))

#############################################################################
#
# Compiler options
#
#############################################################################

# Java compiler flags
JAVACFLAGS	=	-g

#############################################################################
#
# Rules
#
#############################################################################

.DEFAULT_GOAL = all

# Build target: create the executable (runs the main class)
$(NAME): $(OBJ)
	@echo -e "-----\nCreating Executable $(_YELLOW)$@$(_WHITE) ... \c"
	@mkdir -p $(BIN_DIR)
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"
	@echo -e "$(_BLUE)Running $(_YELLOW)Simulation$(_WHITE) with ../scenario.txt ... \c"
	@$(JAVA) -cp $(BIN_DIR) Simulation ../scenario.txt
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"

# Build .class files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@echo -e "Compiling $(_YELLOW)$<$(_WHITE) ... \c"
	@mkdir -p $(BIN_DIR)/$(shell dirname $@)
	@$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<
	@echo -e "$(_GREEN)DONE$(_WHITE)"

# Build all targets (compile all .java files)
all: $(OBJ)
	@echo -e "$(_GREEN)All Java files compiled successfully.$(_NC)"

# Show macro details
show:
	@echo -e "$(_BLUE)SRC_DIR :\n$(_YELLOW)$(SRC_DIR)$(_WHITE)"
	@echo -e "$(_BLUE)PACKAGE_DIRS :\n$(_YELLOW)$(PACKAGE_DIRS)$(_WHITE)"
	@echo -e "$(_BLUE)SRC :\n$(_YELLOW)$(SRC)$(_WHITE)"
	@echo -e "$(_BLUE)OBJ :\n$(_YELLOW)$(OBJ)$(_WHITE)"
	@echo -e "$(_BLUE)JAVACFLAGS :\n$(_YELLOW)$(JAVACFLAGS)$(_WHITE)"
	@echo -e "$(_BLUE)BIN_DIR :\n$(_YELLOW)$(BIN_DIR)$(_WHITE)\n-----"
	@echo -e "$(_BLUE)Compilation Command (example): \n$(_YELLOW)$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/avaj_launcher/util/CoordinatesValidator.java$(_WHITE)"
	@echo -e "$(_BLUE)Execution Command (example): \n$(_YELLOW)$(JAVA) -cp $(BIN_DIR) Simulation ../scenario.txt$(_WHITE)"


# Remove compiled classes
clean:
	@echo -e "$(_WHITE)Deleting Compiled Classes Directory $(_YELLOW)$(BIN_DIR)$(_WHITE) ... \c"
	@rm -rf $(BIN_DIR)
	@echo -e "$(_GREEN)DONE$(_WHITE)\n-----"

# Remove everything built
fclean: clean
	@echo -e "$(_WHITE)Nothing extra to remove for Java project.$(_NC)"

# Remove and rebuild everything
re: fclean all

.PHONY: all show clean fclean re

.ONESHELL:

#############################################################################
#
# Makefile misc
#
#############################################################################

# Colors (using ANSI escape codes)
_GREY=   \033[0;37m
_RED=    \033[0;31m
_GREEN=  \033[0;32m
_YELLOW= \033[0;33m
_BLUE=   \033[0;34m
_PURPLE= \033[0;35m
_CYAN=   \033[0;36m
_WHITE=  \033[0;37m
_NC=     \033[0m

# Colored messages
SUCCESS=$(_GREEN)SUCCESS$(_NC)
COMPILING=$(_BLUE)COMPILING$(_NC)

#############################################################################