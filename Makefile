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

# Find Simulation.java and SimulationBonus.java at the top level of src
SIMULATION_SRC = $(wildcard $(SRC_DIR)/Simulation.java)
SIMULATION_BONUS_SRC = $(wildcard $(SRC_DIR)/SimulationBonus.java)

# Output directory for compiled classes
BIN_DIR		=	bin

# Create a list of .class files for regular sources
OBJ			=	$(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(filter-out $(SIMULATION_SRC) $(SIMULATION_BONUS_SRC), $(SRC)))

# Create .class files for Simulation.java and SimulationBonus.java
SIMULATION_OBJ = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SIMULATION_SRC))
SIMULATION_BONUS_OBJ = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SIMULATION_BONUS_SRC))

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

# Help target: Display available commands
help:
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@echo "  all         Compile all Java source files."
	@echo "  $(NAME)    Create the main executable."
	@echo "  run         Run the main Simulation with ./scenario.txt."
	@echo "  bonus       Create the bonus executable."
	@echo "  runBonus    Run the SimulationBonus with ./scenario.txt."
	@echo "  show        Display Makefile macro details."
	@echo "  clean       Remove compiled class files."
	@echo "  fclean      Remove all build artifacts (currently same as clean)."
	@echo "  re          Rebuild everything from scratch."
	@echo ""
	@echo "Variables (can be overridden):"
	@echo "  JAVAC       Java compiler command (default: $(JAVAC))"
	@echo "  JAVA        Java runtime command (default: $(JAVA))"
	@echo "  JAVACFLAGS  Java compiler flags (default: $(JAVACFLAGS))"
	@echo "  SRC_DIR     Source code directory (default: $(SRC_DIR))"
	@echo "  BIN_DIR     Output directory for compiled classes (default: $(BIN_DIR))"
	@echo ""
	@echo "Colors:"
	@echo "  $(_GREEN)Green$(_NC) - Success messages"
	@echo "  $(_BLUE)Blue$(_NC)  - Information messages"
	@echo "  $(_YELLOW)Yellow$(_NC) - Important names/paths"
	@echo ""
	@echo "Example:"
	@echo "  make run"

# Build target: create the executable (runs the main Simulation)
$(NAME): $(OBJ) $(SIMULATION_OBJ)
	@echo -e "-----\nCreating Executable $(_YELLOW)$@$(_WHITE) ..."
	@mkdir -p $(BIN_DIR)
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"

# Run target for the main Simulation
run: $(NAME)
	@echo -e "$(_BLUE)Running $(_YELLOW)Simulation$(_WHITE) with ./scenario.txt ..."
	@$(JAVA) -cp $(BIN_DIR) Simulation ./scenario.txt
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"

# Build target: create the bonus executable (runs the SimulationBonus)
bonus: $(OBJ) $(SIMULATION_BONUS_OBJ)
	@echo -e "-----\nCreating Bonus Executable $(_YELLOW)$(NAME)_bonus$(_WHITE) ..."
	@mkdir -p $(BIN_DIR)
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"

# Run target for the bonus Simulation
runBonus: bonus
	@echo -e "$(_BLUE)Running $(_YELLOW)SimulationBonus$(_WHITE) with ./scenario.txt ..."
	@$(JAVA) -cp $(BIN_DIR) SimulationBonus ./scenario.txt 
	@echo -e "$(_GREEN)DONE$(_NC)\n-----"

# Build .class files for regular sources
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@echo -e "Compiling $(_YELLOW)$<$(_WHITE) ... \c"
	@mkdir -p $(BIN_DIR)/$(shell dirname $@)
	@$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<
	@echo -e "$(_GREEN)DONE$(_WHITE)"

# Build .class file for Simulation.java
$(BIN_DIR)/Simulation.class: $(SRC_DIR)/Simulation.java
	@echo -e "Compiling $(_YELLOW)$<$(_WHITE) ... \c"
	@mkdir -p $(BIN_DIR)
	@$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<
	@echo -e "$(_GREEN)DONE$(_WHITE)"

# Build .class file for SimulationBonus.java
$(BIN_DIR)/SimulationBonus.class: $(SRC_DIR)/SimulationBonus.java
	@echo -e "Compiling $(_YELLOW)$<$(_WHITE) ... \c"
	@mkdir -p $(BIN_DIR)
	@$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<
	@echo -e "$(_GREEN)DONE$(_WHITE)"

# Build all targets (compile all .java files)
all: $(OBJ) $(SIMULATION_OBJ)

# Show macro details
show:
	@echo -e "$(_BLUE)SRC_DIR :\n$(_YELLOW)$(SRC_DIR)$(_WHITE)"
	@echo -e "$(_BLUE)PACKAGE_DIRS :\n$(_YELLOW)$(PACKAGE_DIRS)$(_WHITE)"
	@echo -e "$(_BLUE)SRC :\n$(_YELLOW)$(SRC)$(_WHITE)"
	@echo -e "$(_BLUE)SIMULATION_SRC :\n$(_YELLOW)$(SIMULATION_SRC)$(_WHITE)"
	@echo -e "$(_BLUE)SIMULATION_BONUS_SRC :\n$(_YELLOW)$(SIMULATION_BONUS_SRC)$(_WHITE)"
	@echo -e "$(_BLUE)OBJ :\n$(_YELLOW)$(OBJ)$(_WHITE)"
	@echo -e "$(_BLUE)SIMULATION_OBJ :\n$(_YELLOW)$(SIMULATION_OBJ)$(_WHITE)"
	@echo -e "$(_BLUE)SIMULATION_BONUS_OBJ :\n$(_YELLOW)$(SIMULATION_BONUS_OBJ)$(_WHITE)"
	@echo -e "$(_BLUE)JAVACFLAGS :\n$(_YELLOW)$(JAVACFLAGS)$(_WHITE)"
	@echo -e "$(_BLUE)BIN_DIR :\n$(_YELLOW)$(BIN_DIR)$(_WHITE)\n-----"
	@echo -e "$(_BLUE)Compilation Command (example): \n$(_YELLOW)$(JAVAC) $(JAVACFLAGS) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/avaj_launcher/util/CoordinatesValidator.java$(_WHITE)"
	@echo -e "$(_BLUE)Execution Command (example): \n$(_YELLOW)$(JAVA) -cp $(BIN_DIR) Simulation ../scenario.txt$(_WHITE)"
	@echo -e "$(_BLUE)Bonus Execution Command (example): \n$(_YELLOW)$(JAVA) -cp $(BIN_DIR) SimulationBonus ../scenario.txt$(_WHITE)"


# Remove compiled classes
clean:
	@echo -e "$(_WHITE)Deleting Compiled Classes Directory $(_YELLOW)$(BIN_DIR)$(_WHITE) ... \c"
	@rm -rf $(BIN_DIR)
	@echo -e "$(_GREEN)DONE$(_WHITE)\n-----"

# Remove everything built
fclean: clean
	@echo -e "$(_WHITE)Nothing extra to remove for Java project.$(_NC)"

# Remove and rebuild everything
re: fclean all bonus

.PHONY: all show clean fclean re run runBonus bonus

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